package com.octest.labo;

import java.util.Set;

import org.openrdf.model.URI;

import slib.graph.algo.utils.GAction;
import slib.graph.algo.utils.GActionType;
import slib.graph.algo.validator.dag.ValidatorDAG;
import slib.graph.io.conf.GDataConf;
import slib.graph.io.conf.GraphConf;
import slib.graph.io.loader.GraphLoaderGeneric;
import slib.graph.io.util.GFormat;
import slib.graph.model.graph.G;
import slib.graph.model.impl.graph.memory.GraphMemory;
import slib.graph.model.impl.repo.URIFactoryMemory;
import slib.graph.model.repo.URIFactory;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.metrics.ic.utils.IC_Conf_Topo;
import slib.sml.sm.core.metrics.ic.utils.ICconf;
import slib.sml.sm.core.utils.SMConstants;
import slib.sml.sm.core.utils.SMconf;
import slib.utils.ex.SLIB_Exception;
import slib.utils.impl.Timer;

/**
 *
 * @author Sébastien Harispe (sebastien.harispe@gmail.com)
 */
public class SMComputationYago {

	public static void main(String[] args) throws SLIB_Exception {

		/*
		 * The data loading is composed of three steps: - (1) we load the yago taxonomy
		 * from the turtle file - (2) we type the vertices in order to specify the
		 * engine which are the vertices associated to classes (concepts) This treatment
		 * is required in order to perform some algorithms. - (3) We root vertices which
		 * are not rooted by owl:Thing. Algorithms require the processed graph to be
		 * connected i.e. to compute the Most Informative Common Ancestors of two
		 * concepts.
		 * 
		 * Notice that due to the size of the taxonomy, extra memory must be allocated
		 * to the JVM e.g. -Xmx3000m
		 */

		Timer t = new Timer();
		t.start();

		String yagoTaxonomyFile = "/data/yago/yagoTaxonomy.ttl";

		URIFactory factory = URIFactoryMemory.getSingleton();
		URI yagoURI = factory.getURI("http://yago-knowledge.org/resource/");
		G g = new GraphMemory(yagoURI);

		// This is the configuration of the data
		GDataConf dataConf = new GDataConf(GFormat.TURTLE, yagoTaxonomyFile);

		// We specify an action to root the vertices, typed as class without outgoing
		// rdfs:subclassOf relationship
		// Those vertices are linked to owl:Thing by an eddge x rdfs:subClassOf
		// owl:Thing
		GAction actionRerootConf = new GAction(GActionType.REROOTING);

		// We now create the configuration we will specify to the generic loader
		GraphConf gConf = new GraphConf();
		gConf.addGDataConf(dataConf);
		gConf.addGAction(actionRerootConf);

		GraphLoaderGeneric.load(gConf, g);

		System.out.println(g.toString());

		// The taxonomy is now a rDAG, i.e. rooted Directed Acyclic Graph.
		// Check by yourself
		Set<URI> roots = new ValidatorDAG().getTaxonomicRoots(g);
		System.out.println("Roots: " + roots);

		// We compute the similarity between two concepts
		URI wikiRugbyFoorballerURI = factory.getURI(yagoURI.stringValue() + "wikicategory_Rugby_footballers");
		URI WordnetSoccerPlayerURI = factory.getURI(yagoURI.stringValue() + "wordnet_soccer_player_110618342");

		// First we configure an intrincic IC
		ICconf icConf = new IC_Conf_Topo(SMConstants.FLAG_ICI_DEPTH_MAX_NONLINEAR);
		// Then we configure the pairwise measure to use, we here choose to use Lin
		// formula
		SMconf smConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_LIN_1998, icConf);

		// We define the engine used to compute the similarity
		SM_Engine engine = new SM_Engine(g);

		double sim = engine.compare(smConf, wikiRugbyFoorballerURI, WordnetSoccerPlayerURI);
		System.out.println("Similarity: " + sim);

		/*
		 * Notice that the first computation is expensive as the engine compute the IC
		 * and extra information which are cached by the engine Let's perform 10000
		 * random computations (we only print some results). We retrieve the set of
		 * vertices as a list
		 */
		/*
		 * int totalComparison = 10000;
		 * 
		 * List<URI> listVertices = new ArrayList<URI>(g.getV()); int nbConcepts =
		 * listVertices.size(); int id1, id2; URI c1, c2; String idC1, idC2; Random r =
		 * new Random();
		 * 
		 * for (int i = 0; i < totalComparison; i++) { id1 = r.nextInt(nbConcepts); id2
		 * = r.nextInt(nbConcepts);
		 * 
		 * c1 = listVertices.get(id1); c2 = listVertices.get(id2);
		 * 
		 * sim = engine.compare(smConf, c1, c2);
		 * 
		 * if ((i + 1) % 1000 == 0) { idC1 = c1.getLocalName(); idC2 =
		 * c2.getLocalName();
		 * 
		 * System.out.println("Sim " + (i + 1) + "/" + totalComparison + "\t" + idC1 +
		 * "/" + idC2 + ": " + sim); } } t.stop(); t.elapsedTime();
		 */

	}

}
