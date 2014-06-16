package org.dbpedia.extraction.mappings

import org.scalatest.{PrivateMethodTester, FlatSpec}
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.dbpedia.extraction.sources.WikiPage
import org.dbpedia.extraction.wikiparser.{Namespace, WikiTitle}
import org.dbpedia.extraction.util.Language

/**
 * Tests the FileTypeExtractor
 */
@RunWith(classOf[JUnitRunner])
class FileTypeExtractorTest extends FlatSpec with ShouldMatchers with PrivateMethodTester {
  "FileTypeExtractor" must "ignore extensions outside the File: namespace" in {
    val pageTitles = List(
        "File but not really.ext"
    )
    val expected = List(
        None
    )

    pageTitles should not be ('empty)

    val extensions = pageTitles.map(
        title => FileTypeExtractor.getExtension(title)
    )

    extensions should be === expected
  }
}
