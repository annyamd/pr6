package com.company.server.server_main;

import com.company.server.model.MusicBand;
import com.company.server.verifiers.Convertions;
import com.company.server.verifiers.CoordinatesVerifier;
import com.company.server.verifiers.MusicBandVerifier;
import com.company.server.verifiers.StudioVerifier;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFile<T> {
    private final String fileName;
    private final File file;
    private final CSVReader csvReader;

    public DataFile(String fileName) throws IOException {
        this.fileName = fileName;
        file = new File(fileName);
        String fileText = readText();
        csvReader = new CSVReader(new StringReader(fileText));

    }

    private String readText() throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        StringBuilder csv = new StringBuilder();
        while (scanner.hasNextLine()) {
            csv.append(scanner.nextLine()).append("\n");
        }
        return csv.toString();
    }

    public List<T> readDataList() {
        return new CsvToBeanBuilder(csvReader)
                .withIgnoreEmptyLine(true)
                .withIgnoreLeadingWhiteSpace(true)
                .withOrderedResults(true)
                .withVerifier(o -> {
                    boolean verify = false;
                    MusicBand mb = (MusicBand) o;

                    mb.setName(Convertions.convertNullableStrToNull(mb.getName()));
                    mb.getStudio().setName(Convertions.convertNullableStrToNull(mb.getStudio().getName()));
                    mb.getStudio().setAddress(Convertions.convertNullableStrToNull(mb.getStudio().getAddress()));

                    if (MusicBandVerifier.verifyId(mb.getId())
                            && MusicBandVerifier.verifyName(mb.getName())
                            && MusicBandVerifier.verifyCreationDate(mb.getCreationDate())
                            && MusicBandVerifier.verifyNumberOfParticipants(mb.getNumberOfParticipants())
                            && MusicBandVerifier.verifyGenre(mb.getGenre())
                            && CoordinatesVerifier.verifyX(mb.getCoordinates().getX())
                            && CoordinatesVerifier.verifyY(mb.getCoordinates().getY())
                    ) {
                        if (StudioVerifier.verifyName(mb.getStudio().getName()) && StudioVerifier.verifyAddress(mb.getStudio().getAddress())) {
                            verify = true;
                        } else if (mb.getStudio().getName() == null && mb.getStudio().getAddress() == null) {
                            mb.setStudio(null);
                            verify = true;
                        }
                    }
                    if (verify) return true;
                    return false;
                })
                .withType(MusicBand.class)
                .withExceptionHandler(e -> {
                    System.out.println("Wrong data in the file \"" + fileName + "\".");
                    System.exit(1);
                    return null;
                }).build().parse();
    }

    public void save(List<T> listToSave){
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName))) {
            List<T> list = new ArrayList<>(listToSave);

            StringWriter stringWriter = new StringWriter();
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(stringWriter).build();
            beanToCsv.write(list);

            writer.write(stringWriter.toString());
        } catch (IOException e){
            System.out.println("Can't save, problem with file.");
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e2){
            e2.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
}
