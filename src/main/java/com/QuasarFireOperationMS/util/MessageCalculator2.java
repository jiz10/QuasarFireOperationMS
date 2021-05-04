package com.QuasarFireOperationMS.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author jiz10
 * 3/5/21
 */

@Slf4j
@Service
public class MessageCalculator2 {

    public String getMessage(List<String> list1, List<String> list2, List<String> list3) {
                /*String[] array1 = {"", "este", "", "", "mensaje", "", ""};
        String[] array2 = {"este", "es", "un", "mensaje", "", "anterior"};
        String[] array3 = {"", "este", "es", "", "mensaje", "nuevo", ""};*/

        int iterations = obtainIterations(list1, list2, list3);
        int it;

        List<String> ngramList1 = new ArrayList<>();
        List<String> ngramList2 = new ArrayList<>();
        List<String> ngramList3 = new ArrayList<>();

        List<String> listReplaced1 = new ArrayList<>(list1);
        List<String> listReplaced2 = new ArrayList<>(list2);
        List<String> listReplaced3 = new ArrayList<>(list3);

        try {
            for (it = 1; it < iterations; it++) {

                log.info("Ngram's order: " + (it + 1));
                ngramList1 = makeNgram(listReplaced1, (it + 1));
                log.info("Ngram 1: " + ngramList1);

                ngramList2 = makeNgram(listReplaced2, (it + 1));
                log.info("Ngram 2: " + ngramList2);

                ngramList3 = makeNgram(listReplaced3, (it + 1));
                log.info("Ngram 3: " + ngramList3);

                ///////OBTENGO FRECUENCIAS
                HashMap<String, Integer> map = countFrequency(ngramList1, ngramList2, ngramList3, it + 1);

                /////OBTENGO N-GRAMA MAS FRECUENTE
                List<String> masFrecuente = obtainMostFrequent(map);

                log.info("Replacing List 1: ");
                listReplaced1 = replaceNgramOnLists(masFrecuente, listReplaced1);

                log.info("Replacing List 2: ");
                listReplaced2 = replaceNgramOnLists(masFrecuente, listReplaced2);
                log.info("Replacing List 3: ");
                listReplaced3 = replaceNgramOnLists(masFrecuente, listReplaced3);

            }

            log.info("List 1 Final replaced: " + listReplaced1);
            log.info("List 2 Final replaced: " + listReplaced2);
            log.info("List 3 Final replaced: " + listReplaced3);

            return makeMessage(listReplaced1, listReplaced2, listReplaced3);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static int obtainIterations(List<String> list1, List<String> list2, List<String> list3) {

        int cantIterations = list1.size();

        if (list2.size() < cantIterations) {
            cantIterations = list2.size();
        } else if (list3.size() < cantIterations) {
            cantIterations = list3.size();
        }

        log.info("Max de iteraciones posibles: " + cantIterations);
        return cantIterations;
    }

    private static List<String> makeNgram(List<String> wordsList, int n) {
        int i = 0;
        ArrayList<String> nGramList = new ArrayList<String>();
        for (String value : wordsList) {
            if (i <= wordsList.size() - n) {
                String nGram = "";
                for (int j = 0; j < n - 1; j++)
                    nGram += wordsList.get(i + j) + " ";
                nGram += wordsList.get(i + n - 1);
                nGramList.add(nGram);
                i++;
            }
        }

        return nGramList;
    }

    private static HashMap<String, Integer> countFrequency(List<String> nGramList1, List<String> nGramList2, List<String> nGramList3, int n) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (String value : nGramList1) {
            List<String> listValues = Arrays.asList(value.split(" "));
            if (!listValues.contains("") && listValues.size() == n) {
                Integer count = map.get(value);
                if (count == null) {
                    count = 0;
                }
                //log.info("Add ngram: " + value);
                map.put(value, count + 1);
            }
        }

        for (String value : nGramList2) {
            List<String> listValues = Arrays.asList(value.split(" "));
            if (!listValues.contains("") && listValues.size() == n) {
                Integer count = map.get(value);
                if (count == null) {
                    count = 0;
                }
                log.info("Add ngram: " + value);
                map.put(value, count + 1);
            }
        }

        for (String value : nGramList3) {
            List<String> listValues = Arrays.asList(value.split(" "));
            if (!listValues.contains("") && listValues.size() == n) {
                Integer count = map.get(value);
                if (count == null) {
                    count = 0;
                }
                log.info("Add ngram: " + value);
                map.put(value, count + 1);
            }
        }

        log.info("Map values: " + map);
        return map;
    }

    private static List<String> obtainMostFrequent(HashMap<String, Integer> map) {

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                if (!(entry.getKey().split(" ").length < 2)) {
                    List<String> listaKey = Arrays.asList(entry.getKey().split(" "));
                    if (!listaKey.contains(""))
                        maxEntry = entry;
                }
            }
        }
        log.info("Max entry: " + maxEntry);
        return Arrays.asList(maxEntry.getKey().split(" "));
    }

    private static List<String> replaceNgramOnLists(List<String> ngramsMostFrequent, List<String> listToReplace) {
        //Por cada elemento de la lista split, ej: "un" reemplezar la siguiente posicion si está vacío
        int i, j, l, m, n;
        //log.info("LISTA A REEMPLAZAR EN FC:  " + listToReplace);
        for (i = 0; i < ngramsMostFrequent.size() - 1; i++) {
            //log.info("Frecuente: " + ngramsMostFrequent.get(i));
            for (j = 0; j < listToReplace.size() - 1; j++) {
                //log.info("Arreglo1: " + lista1.get(j));

                //Si son iguales reemplazamos por la siguiente
                if (ngramsMostFrequent.get(i).equalsIgnoreCase(listToReplace.get(j))) {
                    //log.info("Encuentro palabra: " + ngramsMostFrequent.get(i));
                    if (ngramsMostFrequent.get(i + 1) != null && listToReplace.get(j + 1) != null && listToReplace.get(j + 1) == "") {
                        //log.info("Reemplazamos la siguiente de la lista de frecuentes en la posicion siguiente del arreglo");
                        listToReplace.set(j + 1, ngramsMostFrequent.get(i + 1));
                    }
                }
            }
        }

        log.info("Reversing lists");
        Collections.reverse(listToReplace);
        Collections.reverse(ngramsMostFrequent);

        for (l = 0; l < ngramsMostFrequent.size() - 1; l++) {
            //log.info("Frecuente: " + ngramsMostFrequent.get(l));
            for (m = 0; m < listToReplace.size() - 1; m++) {
                //log.info("Arreglo1: " + listToReplace.get(m));

                //Si son iguales reemplazamos por la siguiente
                if (ngramsMostFrequent.get(l).equalsIgnoreCase(listToReplace.get(m))) {
                    //log.info("Encuentro palabra: " + ngramsMostFrequent.get(l));
                    //log.info("j+1:" + listToReplace.get(j + 1));
                    if (ngramsMostFrequent.get(l + 1) != null && listToReplace.get(m + 1) != null && listToReplace.get(m + 1) == "") {
                        //log.info("Reemplazamos la siguiente de la frecuente");
                        listToReplace.set(m + 1, ngramsMostFrequent.get(l + 1));
                    }
                }
            }
        }
        Collections.reverse(ngramsMostFrequent);
        Collections.reverse(listToReplace);
        log.info("List replaced on fc replaceNgramOnLists(): " + listToReplace);
        return listToReplace;
    }

    private static String makeMessage(List<String> listReplaced1, List<String> listReplaced2, List<String> listReplaced3) {

        List<String> listToUse = new ArrayList<>();

        listToUse = listReplaced1;

        if (listReplaced2.size() < listToUse.size()) {
            listToUse = listReplaced2;
        } else if (listReplaced3.size() < listToUse.size()) {
            listToUse = listReplaced3;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : listToUse) {
            if (value != "") {
                sb.append(value);
                sb.append(" ");
            }
        }
        log.info(sb.toString());
        return sb.toString();
    }

}
