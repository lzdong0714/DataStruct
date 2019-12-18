package think.in.java.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        File filepath =  new File(".");
        String[] list;

        if(args.length == 0){
            list = filepath.list();
        }else {
            list = filepath.list(new DirFileFilter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String item : list){
            System.out.println(item);
        }
    }
}
class DirFileFilter implements FilenameFilter{

    private Pattern pattern;

    public DirFileFilter(String regex){
        pattern = Pattern.compile("a\\w+");
    }

    @Override
    public boolean accept(File dir, String name) {
        boolean b = pattern.matcher(name).matches();
        return b;
    }
}
