package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    static String ge;

    static String bv;

    static String kh;

    public static String getGreenMessage(String words) {

        String[] sad = words.split(" ");
        String word = sad[sad.length - 1];

        System.out.println(word);
        try {
            String anser;


            String[] ss = ge.split(word);


            if (ss.length < 2) {
                System.out.println("1 не найдено");
                ss = bv.split(word);


                if (ss.length < 2) {
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

        } catch (Exception e) {
            System.out.println("3 не найдено");
            e.printStackTrace();
        }
        return "Дедушка тебя не понимает \uD83C\uDF85";
    }


    private ArrayList<String> splitBook() {
        ge.replace("\n\n", "\n");
        //ge.replace("\r","");
        return new ArrayList<>(Arrays.asList(ge.split("\r\n")));
        //return new ArrayList<>(Arrays.asList(ge.split("(?<=\\.)(.*)(?=[А-Я])")));//между точкой и большой буквой выделяет пробел и по нему режет

    }

    private ArrayList<String> getSentences(String word) {
        ArrayList<String> senlist = splitBook();
        Stream<String> result = senlist.stream().filter((s) -> s.contains(word));
        return (ArrayList<String>) result.collect(Collectors.toList());
    }


    public String genBookAnswer(String word) {
        HashSet<String> result = new HashSet<>();
        String resultStr = "";

        ArrayList<String> match = getSentences(word);

        if (!match.isEmpty()) {
            //ArrayList<String> result = new ArrayList<>();
            Random random = new Random();
//            int r = random.nextInt(match.size()-1);
//            result.add(match.get(r));
//            resultStr+=match.get(r)+"\n";
            for (int i = 0; i < 1; i++) {
                if (match.size() == 1) {
                    String s = match.get(0);
                   // resultStr += (s.substring(s.indexOf(word), s.length() - 1));
                    resultStr +=  s;
                } else {
                    int rn = random.nextInt(match.size() - 1);
                    if (!result.contains(match.get(rn))) {
                        result.add(match.get(rn));
                        String s = match.get(rn);
                        resultStr += (s.substring(s.indexOf(word), s.length() - 1)) + "\n";
                    }
                }
            }
            return resultStr;

        } else {
            return "Ничиво не нашел(";
        }

//        Гет сентенс внутри исполняет гетсентенсес, получает массив совпадений и выбирает рандомное или даже конкатенирует 2-3 рандомных
//        Типа из списка
//        If список не пуст{
//            r = рандом(size-1)
//            Set.add(r)
//            S+ Get(r)
//            For(5) {
//                Rnext = рандом(size-1)
//                If (!set.cont rnext)
//                Set.add r2
//                S + Гет(рnext
//            }
//        }else if request try(
//                Дедушка
//        )

    }

}
