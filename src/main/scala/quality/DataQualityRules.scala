// -------------------------------------------------------------
// FICHIER : quality/DataQualityRules.scala
// RÔLE    : Contrôle qualité des données
// LAYER   : SILVER
// -------------------------------------------------------------

package quality

import org.apache.spark.sql.{Column, DataFrame}
import org.apache.spark.sql.functions._

object DataQualityRules {

  /** Vérifie qu'une colonne n'est pas nulle */
  def notNull(colName: String): Column = col(colName).isNotNull

  /** Vérifie que les valeurs numériques sont positives */
  def positive(colName: String): Column = col(colName) >= 0

  /**
   * Applique l'ensemble des règles qualité
   * Les lignes non conformes sont supprimées
   */
  def applyRules(df: DataFrame): DataFrame = {
    df.filter(
      notNull("country") &&
      notNull("cases") &&
      positive("cases") &&
      positive("deaths") &&
      positive("recovered")
    )
  }
}