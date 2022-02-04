package org.example;

public class Generator {

    static String ge;

    static String bv;

    static String kh;

    public static String getGreenMessage(String words){

        String[] sad = words.split(" ");
        String word = sad[sad.length - 1];

        System.out.println(word);
        try {
            String anser;



            String[] ss = ge.split(word);


            if (ss.length <2){
                System.out.println("1 не найдено");
                ss = bv.split(word);


                if (ss.length <2){
                    System.out.println("2 не найдено");
                    ss = kh.split(word);



                    String[] sss = ss[1].split("\n");
                    anser = word + sss[0];
                    return anser;
                }


                String[] sss = ss[1].split("\n");
                anser = word + sss[0];
                return anser;
            }

            String[] sss = ss[1].split("\n");
            anser = word + sss[0];
            return anser;

        }catch (Exception e){
            System.out.println("3 не найдено");
            e.printStackTrace();
        }
        return "Дедушка тебя не понимает \uD83C\uDF85";
    }

}
