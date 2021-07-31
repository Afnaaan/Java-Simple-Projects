package LabExercise2;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static java.lang.Character.isLetter;

public class StringUtil {
    public static boolean containsAlphapet (String s)
    {
        for (Character c: s.toCharArray()) {

            if( !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }




    public static String betterString(String s1, String s2, BiPredicate<String, String> fn)
    {
        if (fn.test(s1, s2))
            return s1;
        else
            return s2;
    }
    public static boolean compareStrings(String s1, String s2)
    {
        if(s1.compareTo(s2) > 0)
            return true;
        else
            return false;
    }


    public static boolean checkString(String s, Predicate<String> p)
    {
        return p.test(s);
    }
    public static boolean isAlphapeticOnly(String s, Predicate<Character> p)
    {
        for(Character l:s.toCharArray())
        {
            if(!p.test(l))
                return false;
        }
        return true;
    }
}
