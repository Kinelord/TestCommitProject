package com.web.config;


/**
 * @author igor@shakirov-dev.ru on 05.01.2023
 * @version 1.0
 */

public class FileCreate {

}
/*

 Как вообще создавать файл или по аналогии директорию разница не существенна (для лучшего восприятия):
 
 В зависимости от того, что должен представлять объект File - файл или каталог, мы можем использовать один из конструкторов для создания объекта:
 Для java.io:
 
 File(String путь_к_каталогу)
 File(String путь_к_каталогу, String имя_файла)
 File(File каталог, String имя_файла)
------example-----
         / создаем объект File для каталога
 File dir1 = new File("C://SomeDir");
 // создаем объекты для файлов, которые находятся в каталоге
 File file1 = new File("C://SomeDir", "Hello.txt");
 File file2 = new File(dir1, "Hello2.txt");

-----
 // создадим новый файл
 File newFile = new File("C://SomeDir//MyFile");
        try
 {
  boolean created = newFile.createNewFile();
  if(created)
   System.out.println("File has been created");
 }
        catch(IOException ex){
  
  System.out.println(ex.getMessage());
 }
 
 
 Для java.nio:
 
 Одним из наиболее важных применений класса Files является создание новых каталогов с использованием метода createDirectory.  Создание каталога довольно простой и понятный процесс, поэтому объяснять особо нечего. Как обычно, всегда полезно использовать метод проверки, exists классе Files для обеспечения возможности создания каталога с заданным путем, а также для предотвращения исключения FileAlreadyExistsException . Вся ситуация представлена ​​в следующем фрагменте кода:
 
 Path newDirectoryPath = Paths.get("/home/jstas/directory");
 if (!Files.exists(newDirectoryPath)) {
  try {
   Files.createDirectory(newDirectoryPath);
  } catch (IOException e) {
   System.err.println(e);
  }
 }
 Пример кода довольно прост — он создает каталог с указанным путем, если никакой другой элемент файловой системы
 не находится на указанном пути. Если нам нужно создать целую иерархию каталогов, то нам нужно переключиться на метод createDirectories  Для создания файла нам нужно снова использовать класс Files и вызвать метод createFile.
 Точно так же как каталог, файл может быть создан с начальными атрибутами файла, и применяются те же ограничения.
 редставлено в следующем примере:
 
 Path newFilePath = Paths.get("C:", "a.txt");
 
if (!Files.exists(newFilePath)) {
  try {
   Files.createFile(newFilePath);
  } catch (IOException e) {
   System.err.println(e);
  }
 }
 
 Пожалуйста, обратите внимание на использование метода проверки exists который предотвращает FileAlreadyExistsException
 
 Дополнительные примеры:
 
 Создайте файл с классом java.io.File
 
 File file = new File("c://temp//testFile1.txt");
//create the file.
if (file.createNewFile()){
  System.out.println("File is created!");
 }
else{
  System.out.println("File already exists.");
 }
 //write content
 FileWriter writer = new FileWriter (file);
writer.write("Test data");
writer.close();
 
 Создайте файл с классом java.io.FileOutputStream:
 
 String data = "Test data";
 FileOutputStream out = new FileOutputStream("c://temp//testFile2.txt");
out.write(data.getBytes());
<span>out.close();
 
 
 Создайте файл с помощью Java.nio.file.Files – Java NIO:
 
 Метод:
 
 public static Path createFile(Path path, FileAttribute<?>... attrs) throws IOException
 
 Создает новый и пустой файл, и если файл уже существует, то будет ошибка.
         Параметры:
 путь – путь для создания файла.
 attrs – необязательный список атрибутов файла, устанавливаемых атомарно при создании.
         
         Например:
 
 String data = "Test data";
Files.write(Paths.get("c://temp//testFile3.txt");
data.getBytes());
 //or
 List<String> lines = Arrays.asList("1st line", "2nd line");
Files.write(Paths.get("file6.txt");
 lines,
 StandardCharsets.UTF_8,
 StandardOpenOption.CREATE,
 StandardOpenOption.APPEND);
 
 Java также может создавать временные файлы:
 
 Создание временного файла с использованием java.io.File.createTempFile()
 
 Public class TemporaryFileExample{
  Public static void main(string[] args){
   try{
    final path path = Files.createTempFile("myTempFile",".txt");
    System.out.println("Temp file : " + path);
// delete file on exist.
    path.toFile().deleteonExit();
   } catch (IOException e){
    e.printStackTrace();
   }
  }
 }
 
 с использованием NIO:
 
 Public class TemporaryFileExample{
  Public static void main(string[] args){
   File temp;
   try{
    temp = File.createTempFile("myTempFile" , ".txt");
    System.out.println("Temp file created : " +
            temp.getAbsolutePath());
   } catch (IOException e){
    e.printStackTrace();
   }
  }
 }
 
 Для создания временного файла используются следующие два метода.
         1. createTempFile(Path, String, String, FileAttribute<?>… attrs) – создает файл tmp в указанном каталоге.
 
 Вышеуказанный метод принимает четыре аргумента.
         Путь -> указать каталог, в котором будет создан файл.
 Строка -> чтобы упомянуть префикс имени файла. Используйте ноль, чтобы избежать префикса.
 Строка -> чтобы упомянуть суффикс имени файла. т.е. расширение файла. Используйте null, чтобы использовать .tmp в качестве расширения.
 attrs -> Это необязательно, чтобы упоминать список атрибутов файла, чтобы установить атомарно при создании файла
Например. Files.createTempFile(path,null, null); – создает временный файл с расширением .tmp по указанному пути

2. createTempFile(String, String, FileAttribute<?>) – создает временный файл во временном каталоге по умолчанию системы / сервера.
         
         Например: Files.createTempFile (null, null) – создает временный файл во временной папке по умолчанию в системе.
 В Windows временная папка может быть C:\Users \ username\AppData\Local\Temp,
 где username – ваш идентификатор входа в Windows(имя пользователя).
 
 Источник:
 https://coderlessons.com/articles/java/sozdanie-failov-i-katalogov-v-nio-2
 https://java-blog.ru/osnovy/kak-sozdat-fayl-java

*/
