package think.in.java.util;

import java.io.File;
import java.io.IOException;

public class ProcessFile {

    public interface Strategy{
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessFile(Strategy strategy, String ext){
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String args[]){
        try {
            if(args.length == 0){
                processDirectoryTree(new File("."));
            }else {
                for(String arg : args){
                    File fileArg = new File(arg);
                    if(fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        if(!arg.endsWith("." + ext))
                            arg += "." + ext;

                        strategy.process(new File(arg).getCanonicalFile());
                    }

                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void processDirectoryTree(File root) {
        for (File file : Directory.walk(root.getAbsolutePath(), ".*\\."+ext)){
            strategy.process(file);
        }
    }

    public static void main(String[] args) {
        // 这个策略方法一直保留到最后由编程者来实现完成，但是之前的所有逻辑都完成了
        // 对着security 的设计好好参考
        new ProcessFile(new Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }
}
