// -------------------------------------------------------------
// FICHIER : analytics/GoldAggregations.scala
// RÔLE    : Calcul des indicateurs épidémiologiques
// LAYER   : GOLD
// -------------------------------------------------------------
package analytics

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object GoldAggregations {

  /**
   * Agrégation par continent
   * Production d'indicateurs macro
   */
  def indicators(df: DataFrame): DataFrame = {
    df.groupBy("continent")
      .agg(
        sum("total_cases").as("cases"),
        sum("total_deaths").as("deaths"),
        avg("new_cases").as("avg_new_cases")
      )
  }
}
