public class Comicstore {
    protected String last_name;
    protected Boolean licenze;
    protected int num_prod;

    public Comicstore(String last_name) {
        this.last_name = last_name;
        licenze = true;
        num_prod = 10;
    }

    public Comicstore(String last_name, Boolean licenze, int num_prod) {
        this.last_name = last_name;
        this.licenze = licenze;
        this.num_prod = num_prod;
    }


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Boolean getLicenze() {
        return licenze;
    }

    public void setLicenze(Boolean licenze) {
        this.licenze = licenze;
    }

    public int getNum_prod() {
        return num_prod;
    }

    public void setNum_prod(int num_prod) {
        this.num_prod = num_prod;
    }

    public void printName(){
        System.out.println("привет, меня зовут " + this.last_name);
    }

    public void haveLicenze(){
        if (this.licenze){
            System.out.println("мой магазин имеет лицензию");
        }
        else{
            System.out.println("мой магазин не имеет лицензии");
        }
    }
}
