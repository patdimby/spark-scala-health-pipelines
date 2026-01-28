// -------------------------------------------------------------
// FICHIER : storage/DeltaWriter.scala
// RÔLE    : Persistance des données en Delta Lake
// -------------------------------------------------------------

package storage

import org.apache.spark.sql.DataFrame

object DeltaWriter {

  /**
   * Écrit un DataFrame au format Delta
   * Mode overwrite pour simplifier les runs locaux
   */
  def write(df: DataFrame, path: String): Unit = {
    df.write
      .format("delta")
      .mode("overwrite")
      .save(path)
  }
}