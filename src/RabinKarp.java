public class RabinKarp {
    public void printString(){
        String s = "8264";
        int number = 0;
        for(int i = 0; i < s.length();i++){
            number = 10 * number + (s.charAt(i) - '0');
        }
        System.out.println(number);
    }
    public void append(){
        int number = 8264;
        int R = 10;
        int append_val = 3;
        number = number*R +append_val;
        System.out.println(number);
    }

    public void remove(){
        int number = 8264;
        int R = 10;
        int L = 4;
        int remove_val = 8;
        number = (int) (number - remove_val * Math.pow(R,L-1));
        System.out.println(number);
    }
    public static void main(String[] args) {
//        RabinKarp k = new RabinKarp();
//        k.printString();
//        k.append();
//        k.remove();
        KMP kmp = new KMP("abc");
        kmp.search("abcdefg");
    }
}
