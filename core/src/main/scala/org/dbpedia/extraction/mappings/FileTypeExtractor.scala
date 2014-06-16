package org.dbpedia.extraction.mappings

import java.util.logging.Logger
import org.dbpedia.extraction.destinations.{DBpediaDatasets, Quad}
import org.dbpedia.extraction.ontology.Ontology
import org.dbpedia.extraction.sources.WikiPage
import org.dbpedia.extraction.util.Language
import org.dbpedia.extraction.wikiparser._
import org.dbpedia.extraction.wikiparser.impl.wikipedia.Namespaces
import scala.language.reflectiveCalls

/**
 * Identifies the type used by a File.
 */
class FileTypeExtractor(context: { 
    def ontology: Ontology
    def language : Language
}) extends WikiPageExtractor
{
    private val fileExtensionProperty = context.ontology.properties("fileExtension")
    
    override val datasets = Set(DBpediaDatasets.FileInformation)
    
    override def extract(page: WikiPage, subjectUri: String, pageContext: PageContext) : Seq[Quad] =
    {
        // This interface only applies to File:s.
        if(page.title.namespace != Namespace.File || page.redirect != null) 
            return Seq.empty

        // Add a quad for the file type as guessed from the extension.
        val file_type_quads = FileTypeExtractor.getExtension(page.title.decodedWithNamespace) match {
                case Some(ext) => Seq(
                    new Quad(Language.English, DBpediaDatasets.FileInformation,
                        subjectUri,
                        fileExtensionProperty,
                        ext,
                        page.sourceUri,
                        context.ontology.datatypes("xsd:string")
                    ))
                case None => Seq.empty
            }

        return Seq(file_type_quads).flatten
    }
}

object FileTypeExtractor {
    private val logger = Logger.getLogger(classOf[FileTypeExtractor].getName)
    
    def getExtension(title: String): Option[String] = {
        val extensionRegex = new scala.util.matching.Regex("""\.(\w+)$""", "extension")
        val extensionMatch = extensionRegex.findAllIn(title)

        if(extensionMatch.isEmpty) None else  {
            val extension = extensionMatch.group("extension").toLowerCase

            if(extension.length > 4)
                logger.warning("Page '" + title + "' has an unusually long extension '" + extension + "'")

            Some(extension)
        }
    }
}
