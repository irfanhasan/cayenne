/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.template.parser;

import java.io.StringReader;

import org.apache.cayenne.template.Context;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @since 4.1
 */
public class SQLTemplateParserTest {

    @Test
    public void testSimpleParse() throws Exception {
        Context context = new Context();
        String template = "SELECT * FROM a";

        String sql = parseString(template, context);
        assertEquals(template, sql);
    }

    @Test
    public void testParameterParse() throws Exception {
        Context context = new Context();
        context.addParameter("a", true);
        String template = "SELECT $a FROM a";

        String sql = parseString(template, context);
        assertEquals("SELECT true FROM a", sql);
    }

    @Test
    public void testIfElseParse() throws Exception {
        Context context = new Context();
        context.addParameter("a", true);
        String template = "SELECT #if($a) * #else 1 #end FROM a";

        String sql = parseString(template, context);
        assertEquals("SELECT  *  FROM a", sql);

        context = new Context();
        context.addParameter("a", false);
        template = "SELECT #if($a) * #else 1 #end FROM a";

        sql = parseString(template, context);
        assertEquals("SELECT  1  FROM a", sql);
    }

    @Test
    public void testBindParse() throws Exception {
        Context context = new Context();
        context.addParameter("a", "var");
        context.addParameter("b", "bbb");
        String template = "SELECT #if($a) #bind($a, 'INT' ,2) #else #bind($b, 'CHAR' ,2) #end FROM a";

        String sql = parseString(template, context);
        assertEquals("SELECT  ?  FROM a", sql);
        assertEquals(1, context.getParameterBindings().length);
        assertEquals("var", context.getParameterBindings()[0].getValue());
    }


    @Test
    public void testComplexParse() throws Exception {
        String template = "SELECT * \n" +
                "FROM ME\n" +
                "#if($a) \n" +
                "WHERE \n" +
                "COLUMN1 #bind($helper.cayenneExp($a, 'db:ID_COLUMN1'), 'INT')\n" +
                "     \tAND \n" +
                "COLUMN2 #bind($helper.cayenneExp($a, 'db:ID_COLUMN2'), 'VARCHAR')\n" +
                "#end\n";
        Context context = new Context();
        class Helper {
            public String cayenneExp(Object obj, String exp) {
                return "aaaa";
            }
        }
        context.addParameter("a", "var");
        context.addParameter("helper", new Helper());

        String sql = parseString(template, context);
        assertEquals("SELECT * \n" +
                "FROM ME\n" +
                " \n" +
                "WHERE \n" +
                "COLUMN1 ?\n" +
                "     \tAND \n" +
                "COLUMN2 ?\n\n", sql);
        assertEquals(2, context.getParameterBindings().length);
        assertEquals("aaaa", context.getParameterBindings()[0].getValue());
    }

    @Test
    public void testComplexParse2() throws Exception {
        String tpl = "SELECT " +
                "#result('t0.BIGDECIMAL_FIELD' 'java.math.BigDecimal' 'ec0_0' 'ec0_0' 2), " +
                "#result('t0.ID' 'java.lang.Integer' 'ec0_1' 'ec0_1' 4) " +
                "FROM BIGDECIMAL_ENTITY t0 WHERE {fn ABS( t0.BIGDECIMAL_FIELD)} < #bind($id0 'DECIMAL')";

        Context context = new Context();
        context.addParameter("$id0", 123);
        String sql = parseString(tpl, context);

        assertEquals("SELECT " +
                "t0.BIGDECIMAL_FIELD AS ec0_0, " +
                "t0.ID AS ec0_1 " +
                "FROM BIGDECIMAL_ENTITY t0 WHERE {fn ABS( t0.BIGDECIMAL_FIELD)} < ?", sql);
    }

    @Test
    public void testComplexParse3() throws Exception {
        String tpl = "SELECT " +
                "#result('COUNT(*)' 'java.lang.Long' 'sc0'), " +
                "#result('t0.ARTIST_NAME' 'java.lang.String' 'ec1_0' 'ec1_0' 1), " +
                "#result('t0.DATE_OF_BIRTH' 'java.util.Date' 'ec1_1' 'ec1_1' 91), " +
                "#result('t0.ARTIST_ID' 'java.lang.Long' 'ec1_2' 'ec1_2' -5), " +
                "#result('SUM(t1.ESTIMATED_PRICE)' 'java.math.BigDecimal' 'sc2') " +
                "FROM ARTIST t0 " +
                "LEFT OUTER JOIN PAINTING t1 ON (t0.ARTIST_ID = t1.ARTIST_ID) " +
                "GROUP BY t0.ARTIST_NAME, t0.DATE_OF_BIRTH, t0.ARTIST_ID ORDER BY t0.ARTIST_NAME";
        parseString(tpl, new Context());
    }

    @Test
    public void testNestedBrackets() throws Exception {
        String tpl = "(#bind('A' 'b'))";
        String sql = parseString(tpl, new Context());
        assertEquals("(?)", sql);
    }

    @Test
    public void testQuotes() throws Exception {
        String template = "\"$a\"";
        Context context = new Context();
        context.addParameter("a", "val");
        String sql = parseString(template, context);
        assertEquals("\"val\"", sql);

        template = "'$a'";
        sql = parseString(template, context);
        assertEquals("'val'", sql);
    }

    @Test
    public void testComma() throws Exception {
        String template = "$a,$a";
        Context context = new Context();
        context.addParameter("a", "val");
        String sql = parseString(template, context);
        assertEquals("val,val", sql);
    }

    private String parseString(String template, Context context) throws ParseException {
        SQLTemplateParser parser = new SQLTemplateParser(new StringReader(template));
        Node block = parser.template();
        return block.evaluate(context);
    }

}