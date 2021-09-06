package com.slib.sml;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.URI;

import slib.graph.algo.accessor.GraphAccessor;
import slib.graph.algo.utils.GAction;
import slib.graph.algo.utils.GActionType;
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
import slib.utils.ex.SLIB_Ex_Critic;
import slib.utils.ex.SLIB_Exception;

public class SMComputationOWL {
	String ontoFile;
	String namspace;
	String Semantic_similarity_methode;
	SMconf smConf;
	SM_Engine engine;
	List<URI> listVertices;
	URIFactory factory;

	public SMComputationOWL(String _ontoFile, String _Semantic_similarity_methode) {
		ontoFile = _ontoFile;
		Semantic_similarity_methode = _Semantic_similarity_methode;
	}

	public void initialise() throws SLIB_Exception {
		factory = URIFactoryMemory.getSingleton();
		URI graphURI = factory.getURI("http://graph/");
		G g = new GraphMemory(graphURI);
		GDataConf dataConf = new GDataConf(GFormat.RDF_XML, ontoFile);
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

		// First we configure an intrincic IC
		// ICconf icConf = new IC_Conf_Topo(SMConstants.FLAG_ICI_DEPTH_MAX_NONLINEAR);
		ICconf icConf = new IC_Conf_Topo(SMConstants.FLAG_ICI_SANCHEZ_2011);
		// Then we configure the pairwise measure to use, we here choose to use Lin
		// formula
		smConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_LIN_1998, icConf);
		// SMconf smConf = new SMconf(SMConstants.FLAG_ICI_SANCHEZ_2011, icConf);
		/*
		 * ICconf icConf = new IC_Conf_Topo(SMConstants.FLAG_ICI_SANCHEZ_2011); SMconf
		 * measureConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_LIN_1998,
		 * icConf);
		 */
		// We define the engine used to compute the similarity
		engine = new SM_Engine(g);
		listVertices = new ArrayList<URI>(GraphAccessor.getClasses(g));
		namspace = listVertices.get(0).getNamespace();

	}

	public double getsimilarity_degree(String concept1, String concept2) {
		double sim = 0;
		// We compute the similarity between two concepts

		URI concept1URI = factory.getURI(namspace + concept1);
		URI concept2URI = factory.getURI(namspace + concept2);
		System.out.println(">>>>>" + listVertices.toString());
		if (listVertices.contains(concept1URI) && listVertices.contains(concept2URI)) {
			try {
				sim = engine.compare(smConf, concept1URI, concept2URI);
			} catch (SLIB_Ex_Critic e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// return 0;
			}

			return sim;
		} else
			return 0;

	}

}
