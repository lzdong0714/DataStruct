package dataStructure.BRTress;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// 鏂囦欢鐩稿叧鎿嶄綔
public class FileOperation {

    // 璇诲彇鏂囦欢鍚嶇О涓篺ilename涓殑鍐呭锛屽苟灏嗗叾涓寘鍚殑鎵�鏈夎瘝璇斁杩泈ords涓�
    public static boolean readFile(String filename, ArrayList<String> words){

        if (filename == null || words == null){
            System.out.println("filename is null or words is null");
            return false;
        }

        // 鏂囦欢璇诲彇
        Scanner scanner;

        try {
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                return false;
        }
        catch(IOException ioe){
            System.out.println("Cannot open " + filename);
            return false;
        }

        // 绠�鍗曞垎璇�
        // 杩欎釜鍒嗚瘝鏂瑰紡鐩稿绠�闄�, 娌℃湁鑰冭檻寰堝鏂囨湰澶勭悊涓殑鐗规畩闂
        // 鍦ㄨ繖閲屽彧鍋歞emo灞曠ず鐢�
        if (scanner.hasNextLine()) {

            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }

        return true;
    }

    // 瀵绘壘瀛楃涓瞫涓紝浠巗tart鐨勪綅缃紑濮嬬殑绗竴涓瓧姣嶅瓧绗︾殑浣嶇疆
    private static int firstCharacterIndex(String s, int start){

        for( int i = start ; i < s.length() ; i ++ )
            if( Character.isLetter(s.charAt(i)) )
                return i;
        return s.length();
    }
}