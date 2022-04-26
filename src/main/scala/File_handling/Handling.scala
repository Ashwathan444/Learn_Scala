package File_handling

import java.io._
import org.apache.poi.ss.usermodel.{ DataFormatter, WorkbookFactory, Row }
import scala.collection.mutable.ListBuffer
object Handling extends App{

  println("Hello World!");
  val startTime = currentTime

  val members = new File("members.xlsx")
  val workbook1 = WorkbookFactory.create(members)
  val sheet1 = workbook1.getSheetAt(0)

  val data = new File("data1.xlsx")
  val workbook2 = WorkbookFactory.create(data)
  val sheet2 = workbook2.getSheetAt(0)

  val result = new File("result.xlsx")
  val workbook3 = WorkbookFactory.create(result)
  val sheet3 = workbook3.getSheetAt(0)

  val name_cell = 1
  val age_cell = 2
  val city_cell = 3

  var valid_emails = new ListBuffer[String]()
  var i=0;
  while(sheet1.getRow(i)!=null) {
      valid_emails += sheet1.getRow(i).getCell(0).toString;
      i +=1
  }
  println(valid_emails)
  var count = 0
  var valid_count = 0
  var j=0
  while(sheet2.getRow(j)!=null) {
      var row = sheet2.getRow(j)
      //Validating email
      if(valid_emails.contains(row.getCell(0).toString)) {
        var index = valid_emails.indexOf(row.getCell(0).toString)
        println("Valid entry at " + j);
        println(sheet1.getRow(index).getCell(0).toString)
        var name = row.getCell(name_cell).toString
        var age = row.getCell(age_cell).toString
        var city = row.getCell(city_cell).toString
        //Validating all fields
        if (name == sheet1.getRow(index).getCell(name_cell).toString &&
          age == sheet1.getRow(index).getCell(age_cell).toString &&
          city == sheet1.getRow(index).getCell(city_cell).toString) {
          valid_count += 1
        }
      }
      else {
        var newrow = sheet3.createRow(count)
        newrow.createCell(0).setCellValue(row.getCell(0).toString);
        newrow.createCell(1).setCellValue(row.getCell(1).toString);
        newrow.createCell(2).setCellValue(row.getCell(2).toString);
        newrow.createCell(3).setCellValue(row.getCell(3).toString);
        newrow.createCell(4).setCellValue("Invalid Entry");
        count += 1
      }
      j +=1
  }
  for(k <- 0 to count-1) {
    var disp_row = sheet3.getRow(k)
    for(col <- 0 to 4) {
      print(disp_row.getCell(col).toString + " ")
    }
    println()
  }
  println("Total Valid entries : " + valid_count)
  

  val totalTime = deltaTime(startTime)
  println("Total time : " + totalTime)

  def currentTime = System.currentTimeMillis()

  def deltaTime(t0: Long) = currentTime - t0
  /*
  val row = sheet.getRow(0);
  val cell = row.getCell(0);
  println(cell.toString);
   */

}
