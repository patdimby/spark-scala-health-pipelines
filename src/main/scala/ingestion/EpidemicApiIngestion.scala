// -------------------------------------------------------------
// FICHIER : ingestion/EpidemicApiIngestion.scala
// RÔLE    : Ingestion des données depuis une API REST publique
// LAYER   : BRONZE (données brutes)
// -------------------------------------------------------------
package ingestion

import org.apache.spark.sql.{DataFrame, SparkSession}
import scala.io.Source
import java.net.URL

object EpidemicApiIngestion {

  /**
   * API publique mondiale (ex : COVID-19)
   * Source fiable utilisée par chercheurs et médias
   */
  val apiUrl = "https://disease.sh/v3/covid-19/countries"

  /**
   * Appelle l'API REST, récupère le JSON
   * et le convertit en DataFrame Spark
   */
  def ingest(spark: SparkSession): DataFrame = {
    import spark.implicits._

    // Appel HTTP simple (GET)
    val rawJson = Source.fromURL(new URL(apiUrl)).mkString

    // Conversion en Dataset[String] pour lecture Spark
    val jsonDS = spark.createDataset(Seq(rawJson))

    // Lecture JSON → DataFrame structuré
    spark.read.json(jsonDS)
  }
}