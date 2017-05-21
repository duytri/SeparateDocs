package main.scala

import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.io.BufferedWriter
import java.io.FileWriter

object SeparateDocs {
  def main(args: Array[String]): Unit = {
    val fileIn = new File(args(0)) // args(0) = ./trndocs.dat
    val reader = new BufferedReader(new FileReader(fileIn))

    var name = 0
    var line = reader.readLine()
    while (line != null) {
      println("Writing file " + name + "...")
      val fileOut = new File(args(1) + File.separator + zeroPad(name, 6)) // args(1) = ./output
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

  }

  def zeroPad(number: Int, width: Int): String = {
    val result: StringBuffer = new StringBuffer("")
    for (i <- 0 until width - number.toString.length)
      result.append("0")
    result.append(number)
    result.toString
  }
}