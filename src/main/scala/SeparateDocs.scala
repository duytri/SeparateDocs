package main.scala

import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.io.BufferedWriter
import java.io.FileWriter

object SeparateDocs {
  def main(args: Array[String]): Unit = {
    val fileIn = new File(args(0)) // args(0) = ./trndocs.dat
    var reader: BufferedReader = null
    if (fileIn.isDirectory()) {
      val listFiles = fileIn.listFiles()
      var startFilename = 0
      listFiles.foreach(file => {
        reader = new BufferedReader(new FileReader(file))
        startFilename = writeFile(reader, startFilename, args(1))
      })
    } else {
      reader = new BufferedReader(new FileReader(fileIn))
      writeFile(reader, 0, args(1))
    }

  }

  /**
   * Write separated text files
   * @param reader buffer reader of a file
   * @param startName start number of file name
   * @param outputDir output directory
   * @return number of last file name
   */
  def writeFile(reader: BufferedReader, startName: Int, outputDir: String): Int = {
    var name = startName
    reader.readLine() // skip 01 line
    var line = reader.readLine()
    while (line != null) {
      println("Writing file " + name + "...")
      val fileOut = new File(outputDir + File.separator + zeroPad(name, 6)) // args(1) = ./output
      val writer = new BufferedWriter(new FileWriter(fileOut))
      writer.flush

      val words = line.split(" ")
      words.foreach(w => {
        writer.write(w + "\n")
      })
      writer.close

      name += 1
      line = reader.readLine()
    }
    reader.close()
    name
  }

  def zeroPad(number: Int, width: Int): String = {
    val result: StringBuffer = new StringBuffer("")
    for (i <- 0 until width - number.toString.length)
      result.append("0")
    result.append(number)
    result.toString
  }
}