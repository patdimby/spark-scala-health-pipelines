// -------------------------------------------------------------
// FICHIER : transformation/SilverTransformations.scala
// RÔLE    : Normalisation et structuration des données
// LAYER   : SILVER
// -------------------------------------------------------------

package transformation

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object SilverTransformations {

  /**
   * Renomme les colonnes
   * Sélectionne les champs utiles
   * Ajoute une date d'ingestion
   */
  def normalize(df: DataFrame): DataFrame = {
    df.select(
      col("country").as("country_name"),
      col("continent"),
      col("population"),
      col("cases").as("total_cases"),
      col("todayCases").as("new_cases"),
      col("deaths").as("total_deaths"),
      col("todayDeaths").as("new_deaths"),
      current_timestamp().as("ingestion_ts")
    )
  }
}