//Реализовать цикл с двадцатью итерациями. С помощью операторов ветвления распечатать
//        в столбик четные числа, начиная со второго десятка.
public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            if (i>10 && i%2==0){
                System.out.println(i);
            }
        }
        Comicstore com1 = new Comicstore("Ostsrovski", true, 30);
        Comicstore com2 = new Comicstore("Zharski", false, 100);
        Comicstore com = new Comicstore("Shamrilo");

        com1.printName();
        com2.haveLicenze();

        WriterInfo writer = new WriterInfo();

        writer.printInfo(com1);
    }
}