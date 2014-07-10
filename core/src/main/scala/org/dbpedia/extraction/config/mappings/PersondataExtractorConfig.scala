package org.dbpedia.extraction.config.mappings


object PersondataExtractorConfig
{
  // The French template is not used anymore. See http://fr.wikipedia.org/wiki/Wikipédia:Sondage/Limitation_du_modèle_Métadonnées_personne

  val supportedLanguages = Set("en", "de", "commons")
  val persondataTemplates = Map("en" -> "persondata", "de" -> "personendaten", "commons" -> "creator")
  val name = Map("en" -> "NAME", "de" -> "NAME", "commons" -> "Name")
  val alternativeNames = Map("en" -> "ALTERNATIVE NAMES", "de" -> "ALTERNATIVNAMEN", "commons" -> "Alternative names")
  val description = Map("en" -> "SHORT DESCRIPTION", "de" -> "KURZBESCHREIBUNG", "commons" -> "Description")
  val birthDate = Map("en" -> "DATE OF BIRTH", "de" -> "GEBURTSDATUM", "commons" -> "Birthdate")
  val birthPlace = Map("en" -> "PLACE OF BIRTH", "de" -> "GEBURTSORT", "commons" -> "Birthloc")
  val deathDate = Map("en" -> "DATE OF DEATH", "de" -> "STERBEDATUM", "commons" -> "Deathdate")
  val deathPlace = Map("en" -> "PLACE OF DEATH", "de" -> "STERBEORT", "commons" -> "Deathloc")
}
