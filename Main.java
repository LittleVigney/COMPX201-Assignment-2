public class Main{
    public static void main(String[] args) {
        StrBST myBst = new StrBST();

        // add()
        for (int i = 5; i >= 0; i--){
            myBst.insert(String.valueOf(i));
        }
        for (int i = 6; i <= 10; i++){
            myBst.insert(String.valueOf(i));
        }

        // print()
        myBst.print();

        System.out.println("remove some nodes...");
        // remove()
        myBst.remove("3");
        myBst.remove("1");
        myBst.remove("9");

        myBst.print();
    }
}
