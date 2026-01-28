// -------------------------------------------------------------
// FICHIER : config/SparkSessionBuilder.scala
// RÔLE    : Centralise la configuration Spark
// -------------------------------------------------------------

package config

import org.apache.spark.sql.SparkSession

object SparkSessionBuilder {

  /**
   * Crée et configure une SparkSession.
   * - Active Delta Lake
   * - Mode local pour exécution Windows
   */
  def build(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .master("local[*]") // utilise tous les cœurs disponibles
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
  }
}