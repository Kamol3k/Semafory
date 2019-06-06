import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {


    public class W1 extends Thread{

        public static int a;
        private Semaphore s0;
        private Semaphore s1;

        public W1(Semaphore s0, Semaphore s1) {
            this.s0 = s0;
            this.s1 = s1;
        }

        @Override
        public void run() {

            Scanner in = new Scanner(System.in);

            System.out.println("Podaj pierwsza zmienna");
            a = in.nextInt();

            s0.release();
            s1.release();

        }
    }

    public class W2 extends Thread{

        public static int b;
        private Semaphore s0;
        private Semaphore s2;

        public W2(Semaphore s0, Semaphore s2) {
            this.s0 = s0;
            this.s2 = s2;
        }

        @Override
        public void run() {

            try {
                s0.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Scanner in = new Scanner(System.in);
            System.out.println("Podaj druga zmienna");
            b = in.nextInt();

            s0.release();
            s2.release();

        }


    }

    public class W3 extends Thread{

        private Semaphore s1,s2;

        public W3(Semaphore s1, Semaphore s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {

            try {
                s1.acquire();
                s2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Iloczyn to: " + (W1.a * W2.b));

            s1.release();
            s2.release();

        }
    }


    public static void main(String[] args) {

        Semaphore s0 = new Semaphore(0);
        Semaphore s1 = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);

        new W1(s0,s1).start();
        new W2(s0,s2).start();
        new W3(s1,s2).start();
    }
}
