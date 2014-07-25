package org.nif.neurolex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class ResourceFix implements Runnable {

    private MainFrame ms;
    private String action;
    String Neuorelexattribute;
    String attributes;
    String newAttribute;

    public ResourceFix(MainFrame ms, String action, String Neuorelexattribute, String attributes, String newAttribute) {
        this.ms = ms;
        this.action = action;
        this.Neuorelexattribute = Neuorelexattribute;
        this.attributes = attributes;
        this.newAttribute = newAttribute;
    }

    public static void main(MainFrame ms) throws SQLException {
    }

    public void ReplaceDownloadable(MainFrame ms, String action, String Neuorelexattribute, String oldWords, String newWords) throws SQLException {
        try {

            //Changing connection informations
            MysqlDBService.USERNAME = this.ms.TXTDBUserName.getText().trim();
            MysqlDBService.PASSWORD = this.ms.TXTDBUserPassword.getText();
            MysqlDBService.URL = this.ms.TxtServerURL.getText().trim();
            //
            ms.txtOutput.setText("");
            List<String> output = new ArrayList<String>();
            int counter = 0;
            boolean match = false;
            StringBuilder preview = new StringBuilder();
            List<String> title = new ArrayList<String>();
            List<String> body = new ArrayList<String>();
            String old_has_role = "";
            String SQL;
            SQL = "select convert(p.page_title using utf8) as page_title, convert(t.old_text using utf8) as page_text, convert(r.rev_timestamp, date) from text t, revision r, page p where t.old_id = r.rev_text_id and r.rev_id = p.page_latest and (";

            //Adding the worsds to the SQL statement.
            String[] words = oldWords.split(",");
            for (int k = 0; k < words.length; k++) {
                if (words[k].equals("")) {
                    continue;
                }
                if (ms.CheckSensative.isSelected() == false) {
                    SQL += "convert(t.old_text using utf8) like \"%" + words[k].trim() + "%\" or ";
                } else {
                    SQL += " BINARY convert(t.old_text using utf8) like \"%" + words[k].trim() + "%\" or ";
                }

            }
            SQL = SQL.substring(0, SQL.length() - 3);
            SQL += ")";
            //Fetching Data from the database.  
            ms.txtOutput.append("Fetching data from Neurolex database...\n");
            ResultSet rs = MysqlDBService.getResultSet(SQL);

            ms.txtOutput.append("Analysing Data... \n");
            while (rs.next()) {
                //Counter to show data.
                //System.out.println(counter++);
                // split the body text into into different attributes.
                String[] attribute = rs.getString("page_text").split("\\|");
                for (int i = 0; i < attribute.length; i++) {
                    // Find the attribute that contains "Has role";
                    if (attribute[i].contains(Neuorelexattribute + "=")) {
                        //
                        String[] tail = attribute[i].split("\\n");
                        attribute[i] = tail[0] + "\n";
                        for (int k = 0; k < words.length; k++) {
                            if (words[k].equals("")) {
                                continue;
                                //First Check     
                            }
                            if (ms.CheckSensative.isSelected() == true) {
                                match = attribute[i].contains(words[k].trim());
                            } else {
                                match = attribute[i].toLowerCase().contains(words[k].toLowerCase().trim());
                            }

                            if (match == true) {
                                break;
                            }
                        }
                        if (match) {
                            String copyattribute;
                            old_has_role = attribute[i];
                            //Remove the attribute name
                            attribute[i] = attribute[i].replace(Neuorelexattribute + "=", ",");
                            attribute[i] = attribute[i].trim();
                            //Remove the comma at the end
                            if (attribute[i].substring(attribute[i].length() - 1, attribute[i].length()).equals(",")) {

                                attribute[i] = attribute[i].substring(0, attribute[i].length() - 1);
                            }
                            //Prepare regular expression
                            String[] ReplaceStrings = RegularExpressionPrep(attributes, false, ms);
                            copyattribute = attribute[i];
                            //Replace the old attributes with nothing
                            for (int m = 0; m < ReplaceStrings.length; m++) {
                                //
                                //Handeling the case when paratheses are used.
                                //if (ms.CheckSensative.isSelected() == true) {
                                if (ms.CheckSensative.isSelected() == false) {
                                    ReplaceStrings[m] = ReplaceStrings[m].replace("[((]", "\\(");
                                    ReplaceStrings[m] = ReplaceStrings[m].replace(")", "\\)");
                                } else {
                                    ReplaceStrings[m] = ReplaceStrings[m].replace("[(]", "\\(");
                                    ReplaceStrings[m] = ReplaceStrings[m].replace(")", "\\)");
                                }
                                //
                                //Remove commas;
                                attribute[i] = attribute[i].replace("%2C", ",");
                                attribute[i] = attribute[i].replaceAll(",\\s*" + ReplaceStrings[m] + "\\s*(\\([^(]*\\))*\\s*(,|$)", ",");
                                //Check if a change in the atteribute happend which indicate the presence of a desired attribute
                            }
                            if (copyattribute == attribute[i]) {
                                break;
                            }
                            counter++;
                            preview.append("***************\n" + rs.getString("page_title") + "\nBefore: " + old_has_role);
                            body.add(rs.getString("page_text"));
                            title.add(rs.getString("page_title"));
                            String[] ReplaceWithString = RegularExpressionPrep(newWords, true, ms);

                            //Handeling the case when paratheses are used.
                            ReplaceWithString[0] = ReplaceWithString[0].replace("[((]", "\\(");
                            ReplaceWithString[0] = ReplaceWithString[0].replace(")", "\\)");
                            //
                            //Remove the new attributes if they already exist.
                            attribute[i] = attribute[i].replaceAll(",\\s*" + ReplaceWithString[0] + "\\s*(\\([^(]*\\))*\\s*", "");
                            //attribute[i] = attribute[i].replaceAll(",\\s*" + ReplaceWithString[0] + "\\s*(\\(.*\\))*\\s*", "");
                            //Add the newrolex element to the specefied attribute.
                            attribute[i] = attribute[i] + ", " + newWords;
                            attribute[i] = attribute[i].trim();
                            //Add the attribute back.
                            attribute[i] = Neuorelexattribute + "=" + attribute[i];
                            attribute[i] = attribute[i].replace(Neuorelexattribute + "=,", Neuorelexattribute + "=");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + "= ", Neuorelexattribute + "=");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + "=,", Neuorelexattribute + "=");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + "= ", Neuorelexattribute + "=");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + ",,", Neuorelexattribute + ",");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + ",,", Neuorelexattribute + ",");
                            attribute[i] = attribute[i].replace(Neuorelexattribute + ",,", Neuorelexattribute + ",");
                            attribute[i] = attribute[i].replace(",,", ",");
                            attribute[i] = attribute[i].replace(",,", ",");

                            attribute[i] = attribute[i] + "\n";
                            preview.append("After: " + attribute[i] + "\n");
                            // replace the text the body array  with the new text by calling the index.
                            body.set(body.size() - 1, body.get(body.size() - 1).replace(old_has_role, attribute[i]));

                        }

                    }

                }
            }
            ms.Statuts.setText("");
            ms.txtOutput.append("Records to be updated: " + counter + "\n");
            if (action.equals("preview")) {
                ms.txtOutput.append(preview.toString());
                ms.previewButton.setEnabled(true);
                ms.stopButton.setEnabled(false);
            }
            if (action.equals("update")) {
                UpdateNeurolex(ms, title, body);
            }
        } catch (Exception e) {
            ms.stopButton.setEnabled(false);
            ms.showError(e.getMessage());
            ms.previewButton.setEnabled(true);
            ms.txtOutput.setText("");

        }
    }

    public static void UpdateNeurolex(MainFrame ms, List<String> title, List<String> body) {
        try {
            String preview = "";            
            MediaWikiBot b = new MediaWikiBot(ms.TxtURL.getText().trim());
            //Get Date and log it with usrname and password
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            b.login(ms.TxtWikiUserName.getText().trim(), ms.TxtWikiPassword.getText());
            logger("******************************");
            logger(date.toString());
            logger("UserName: " + ms.TxtWikiUserName.getText());
            logger("Attribute: " + ms.TxtAttribute.getText());
            logger("Attribute: " + ms.TxtAttribute.getText());
            logger("Elements to be replaced: " + ms.TxtElementsReplaced.getText());
            logger("To be replaced with: " + ms.TxtElementsReplacedWith.getText());
            logger("******************************");
            if (body.size() > 0) {
                ms.Statuts.setText((0) + " of " + body.size() + " (" + "0" + "%)");
                for (Integer i = 0; i < body.size(); i++) {
                    Double percent = Double.valueOf(i + 1) / Double.valueOf((body.size()));
                    percent = percent * 100;
                    percent = roundTwoDecimals(percent);

                    ms.txtOutput.append("Updating page " + title.get(i) + "\n");

                    Article article = b.getArticle("Category:" + title.get(i));
                    article.setText(body.get(i));
                    article.save();
                    logger(title.get(i));
                    ms.txtOutput.append("Page Updated!" + "\n");
                    ms.Statuts.setText((i + 1) + " of " + body.size() + " (" + percent + "%)");
                }
            }
            ms.previewButton.setEnabled(true);
            ms.convertButton.setEnabled(true);
            ms.stopButton.setEnabled(false);

        } catch (Exception e) {
            ms.previewButton.setEnabled(true);
            ms.convertButton.setEnabled(true);
            ms.stopButton.setEnabled(false);
            ms.showError(e.getMessage());

        }
    }

    public static String[] RegularExpressionPrep(String elements, boolean replacewith, MainFrame ms) {

        String[] words = elements.trim().split(",");
        //Define the return array size to be the same as the spiliting units.
        String[] returnElements = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            returnElements[i] = "";
            //Split words that have spaces between them.
            String[] subWords = words[i].split(" ");
            for (int j = 0; j < subWords.length; j++) {
                subWords[j] = subWords[j].trim();
                if (subWords[j].equals("")) {
                    continue;
                }
                if (replacewith == true) {
                    subWords[j] = "[" + subWords[j].substring(0, 1).toLowerCase() + subWords[j].substring(0, 1).toUpperCase() + "]" + subWords[j].substring(1);
                } else {
                    if (ms.CheckSensative.isSelected() == true) {
                        subWords[j] = "[" + subWords[j].substring(0, 1) + "]" + subWords[j].substring(1);
                    } else {
                        subWords[j] = "[" + subWords[j].substring(0, 1).toLowerCase() + subWords[j].substring(0, 1).toUpperCase() + "]" + subWords[j].substring(1);
                    }

                }

                returnElements[i] += " " + subWords[j];
                returnElements[i] = returnElements[i].trim();

            }
        }
        return returnElements;

    }

    @Override
    public void run() {
        try {
            this.ReplaceDownloadable(ms, action, Neuorelexattribute, attributes, newAttribute);
            Thread tr = Thread.currentThread();
            while (2 > 1) {
                if (!(tr == ms.tr)) {
                    tr.sleep(1000);
                }
            }
        } catch (Exception e) {
            ms.Statuts.setText(e.getMessage());
        }

    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public static void logger(String log) {
        FileWriter output = null;
        try {
            FileWriter fileWritter = new FileWriter("Log.txt", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            bufferWritter.write(log + "\n");
            bufferWritter.newLine();
            bufferWritter.close();
        } catch (Exception e) {
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
