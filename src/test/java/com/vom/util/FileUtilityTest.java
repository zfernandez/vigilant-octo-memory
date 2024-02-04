package com.vom.util;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilityTest {
    @Test
    public void methodShouldRead() {
        FileUtility util = new FileUtility();
        String badHTMLText = "<html/>";
        StringReader fileContents = new StringReader(badHTMLText);

        assertEquals(badHTMLText, util.readImpl("bad.html", true, false, fileContents));
    }

    @Test
    public void methodShouldWrite() {
        FileUtility util = new FileUtility();
        String badHTMLText = "<html/>";
        StringWriter fileContents = new StringWriter();

        assertEquals(0, util.writeImpl("bad.html", true, false, fileContents, badHTMLText));
    }
}
