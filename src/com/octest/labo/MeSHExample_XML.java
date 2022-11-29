package com.octest.labo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openrdf.model.URI;

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
import slib.utils.impl.Timer;

/**
 *
 * @author Sébastien Harispe (sebastien.harispe@gmail.com)
 */
public class MeSHExample_XML {

	public static void main(String[] args) {

		try {

			Timer t = new Timer();
			t.start();

			URIFactory factory = URIFactoryMemory.getSingleton();
			URI meshURI = factory.getURI("http://www.nlm.nih.gov/mesh/");

			G meshGraph = new GraphMemory(meshURI);

			GDataConf dataMeshXML = new GDataConf(GFormat.MESH_XML, "C:\\Users\\MATHMAMA\\Downloads\\desc2014.xml"); // the
																														// DTD
																														// must
																														// be
			// located in
			// the same
			// directory
			GraphLoaderGeneric.populate(dataMeshXML, meshGraph);

			// System.out.println(meshGraph);

			/*
			 * Now we compute Semantic Similarities between pairs vertices
			 */
			// we first configure a pairwise measure
			ICconf icConf = new IC_Conf_Topo(SMConstants.FLAG_ICI_SANCHEZ_2011);
			SMconf measureConf = new SMconf(SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_LIN_1998, icConf);

			// We define the semantic measure engine to use
			SM_Engine engine = new SM_Engine(meshGraph);

			// We compute semantic similarities between concepts
			// e.g. between Paranoid Disorders (D010259) and Schizophrenia, Paranoid
			// (D012563)
			// http://id.nlm.nih.gov/mesh/D010259

			/**** for auther id see https://www.ncbi.nlm.nih.gov/mesh/2023153 *****/

			URI c1 = factory.getURI("http://www.nlm.nih.gov/mesh/D017941"); // Coronavirus, Rat // Paranoid Disorders
			URI c2 = factory.getURI("http://www.nlm.nih.gov/mesh/D064149"); // sars //

			// Schizophrenia, Paranoid
			// URI c2 = factory.getURI("http://www.nlm.nih.gov/mesh/D010240"); // Parakeets

			// D001523 : Mental Disorders ,Paranoid Disorders 0.7331324832545175

			// We compute the similarity
			double sim = engine.compare(measureConf, c1, c2);
			System.out.println("Sim " + c1 + "\t" + c2 + "\t" + sim);

			// System.out.println(meshGraph.toString());

			/*
			 * The computation of the first similarity is not very fast because the engine
			 * compute extra informations which are cached for next computations. Lets
			 * compute 10 000 000 random pairwise similarities
			 */
			/*
			 * int totalComparison = 10000000; List<URI> concepts = new
			 * ArrayList<URI>(meshGraph.getV()); int id1, id2; String idC1, idC2; Random r =
			 * new Random();
			 * 
			 * for (int i = 0; i < totalComparison; i++) { id1 = r.nextInt(concepts.size());
			 * id2 = r.nextInt(concepts.size());
			 * 
			 * c1 = concepts.get(id1); c2 = concepts.get(id2);
			 * 
			 * sim = engine.compare(measureConf, c1, c2);
			 * 
			 * if ((i + 1) % 50000 == 0) { idC1 = c1.getLocalName(); idC2 =
			 * c2.getLocalName();
			 * 
			 * System.out .println("Sim " + (i + 1) + "/" + totalComparison + "\t" + idC1 +
			 * "/" + idC2 + ": " + sim); } }
			 */
			t.stop();
			t.elapsedTime();
		} catch (SLIB_Exception ex) {
			Logger.getLogger(MeSHExample_XML.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}