package com.aqumon.zzx.lambda.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();

        // TODO 此处替换为本地文件的地址全路径
        String filePath = "D:\\02-DemoWorkSpace\\Java8\\src\\test\\java\\com\\aqumon\\zzx" +
                "\\lambda\\file\\FileServiceTest.java";

        // 通过lambda表达式，打印文件内容
//        fileService.fileHandle(filePath,
//                System.out::println);
        fileService.fileHandle(filePath, fileContent -> System.out.println(fileContent));
    }
}
