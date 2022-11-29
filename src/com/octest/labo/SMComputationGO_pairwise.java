package com.octest.labo;

import org.openrdf.model.URI;

import slib.graph.algo.utils.GAction;
import slib.graph.algo.utils.GActionType;
import slib.graph.algo.utils.GraphActionExecutor;
import slib.graph.io.conf.GDataConf;
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

/**
 *
 * Example of a Semantic measure computation using the Semantic Measures
 * Library. In this snippet we estimate the similarity of two concepts expressed
 * in the Gene Ontology. The Gene Ontology is expressed in OBO format. The
 * similarity is estimated using Lin's measure.
 *
 * More information at http://www.semantic-measures-library.org/
 *
 * Note that you can set the LOG level in specified in log4j.xml, e.g. in root
 * element, change value="INFO" to value="DEBUG"
 *
 * @author Sébastien Harispe (sebastien.harispe@gmail.com)
 */
public class SMComputationGO_pairwise {

	public static void main(String[] params) throws SLIB_Exception {

		// Configuration files, set the file path according to your configuration.
		// The Gene Ontology (OBO format)
		// String goOBO = "/data/go/gene_ontology_ext.obo";

		String goOBO = "C:\\Users\\MATHMAMA\\Downloads\\gene_ontology_ext.obo";

		URIFactory factory = URIFactoryMemory.getSingleton();
		URI graph_uri = factory.getURI("http://go/");

		// We define a prefix in order to build valid uris from ids such as GO:XXXXX,
		// considering the configuration specified below the URI associated to GO:XXXXX
		// will be http://go/XXXXX
		factory.loadNamespacePrefix("GO", graph_uri.toString());

		G graph = new GraphMemory(graph_uri);

		GDataConf graphconf = new GDataConf(GFormat.OBO, goOBO);
		GraphLoaderGeneric.populate(graphconf, graph);

		// General information about the graph
		System.out.println(graph.toString());

		// The Gene Ontology is not rooted, i.e. Molecular Function, Biological Process,
		// Cellular Component, the three sub-ontologies of
		// the GO are not rooted. We create such a virtual root in order to be able to
		// compare
		// the concepts expressed in different sub-ontologies.

		// We create a vertex corresponding to the virtual root
		// and we add it to the graph
		URI virtualRoot = factory.getURI("http://go/virtualRoot");
		graph.addV(virtualRoot);

		// We root the graphs using the virtual root as root
		GAction rooting = new GAction(GActionType.REROOTING);
		rooting.addParameter("root_uri", virtualRoot.stringValue());
		GraphActionExecutor.applyAction(factory, rooting, graph);

		// System.out.println(graph.toString());

		// int nbVertices = graph.getV().size();

		// System.out.println("Nb vertices : " + nbVertices);

		// We compute the similarity between http://go/0071869 and the collection of
		// vertices
		URI concept1 = factory.getURI("http://go/0071869");
		URI concept2 = factory.getURI("http://go/0015747");

		ICconf icConf = new IC_Conf_Topo("Sanchez", SMConstants.FLAG_ICI_SANCHEZ_2011);

		// Then we define the Semantic measure configuration
		SMconf smConf = new SMconf("Lin", SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_LIN_1998);
		smConf.setICconf(icConf);

		SM_Engine engine = new SM_Engine(graph);

		double sim;
		sim = engine.compare(smConf, concept1, concept2);
		System.out.println(concept1 + "\t" + concept2 + "\t" + sim);
		/*
		 * for (URI v : graph.getV()) {
		 * 
		 * sim = engine.compare(smConf, concept, v); System.out.println(concept + "\t" +
		 * v + "\t" + sim); }
		 */
	}
}