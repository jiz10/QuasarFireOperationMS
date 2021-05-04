package com.QuasarFireOperationMS.util;

import com.QuasarFireOperationMS.web.error.CalculationMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiz10
 * 3/5/21
 */
@Slf4j
@Service
public class MessageCalculator {

    public String getMessage(List<String> list1, List<String> list2, List<String> list3) {
           /* String[] array1 = {"", "es", "", "mensaje", "", "secreto"};
        String[] array2 = {"", "", "", "", "mensaje", "", ""};
        String[] array3 = {"", "este", "", "un", "mensaje", "nuevo", ""};*/

        try {
            log.info("Create arrays");
            //Create arrays
            String[] array1 = new String[list1.size()];
            array1 = list1.toArray(array1);
            log.info(Arrays.toString(array1));

            String[] array2 = new String[list2.size()];
            array2 = list2.toArray(array2);
            log.info(Arrays.toString(array2));

            String[] array3 = new String[list3.size()];
            array3 = list3.toArray(array3);
            log.info(Arrays.toString(array3));


            ///Calculate start positions
            int startPosition1 = calculateStartPosition(array1);
            int startPosition2 = calculateStartPosition(array2);
            int startPosition3 = calculateStartPosition(array3);

            log.info("Start position 1: " + startPosition1);
            log.info("Start position 2: " + startPosition2);
            log.info("Start position 3: " + startPosition3);

            //Calcular que array arranca primero
            String firstArrayStarted = "array1";
            int firstPositionStarted = startPosition1;
            String[] arrayContenido = array1;

            if ((startPosition2 < firstPositionStarted) || (startPosition2 == firstPositionStarted && array2.length > arrayContenido.length)) {
                firstPositionStarted = startPosition2;
                firstArrayStarted = "array2";
                arrayContenido = array2;
            }

            if ((startPosition3 < firstPositionStarted) || (startPosition3 == firstPositionStarted && array3.length > arrayContenido.length)) {
                firstPositionStarted = startPosition3;
                firstArrayStarted = "array3";
                arrayContenido = array3;
            }

            log.info("First array started: " + firstArrayStarted);
            log.info("First position en array: " + firstPositionStarted);
            log.info("Array which start first: " + Arrays.toString(arrayContenido));

            //Calcular desfasage

            int arrayFinalLength = arrayContenido.length - firstPositionStarted;
            log.info("Array final lenght: " + arrayFinalLength);

            int offset1 = array1.length - arrayFinalLength;
            int offset2 = array2.length - arrayFinalLength;
            int offset3 = array3.length - arrayFinalLength;

            log.info("Offset 1: " + offset1);
            log.info("Offset 2: " + offset2);
            log.info("Offset 3: " + offset3);

            //Calculate subStrings
            String[] subString1 = calculateSubString(array1, offset1);
            log.info(Arrays.toString(subString1));

            String[] subString2 = calculateSubString(array2, offset2);
            log.info(Arrays.toString(subString2));

            String[] subString3 = calculateSubString(array3, offset3);
            log.info(Arrays.toString(subString3));

            //Replace arrays
            String[] arrayMsg = replaceArrays(subString1, subString2, subString3, arrayFinalLength);
            log.info(Arrays.toString(arrayMsg));

            return buildMessage(arrayMsg);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CalculationMessageException();
        }
    }

    private static int calculateStartPosition(String[] array) {
        int startPosition = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != "" && !array[i].isEmpty()) {
                startPosition = i;
                break;
            }
        }
        return startPosition;
    }

    private static String[] calculateSubString(String[] array, int offset) {

        String[] subString1 = new String[array.length - offset];
        for (int j = 0; j < array.length - offset; j++) {
            subString1[j] = array[j + offset];
        }
        return subString1;
    }

    private static String[] replaceArrays(String[] subString1, String[] subString2, String[] subString3, int arrayFinalLength) {

        String[] arrayMsg = new String[arrayFinalLength];
        int m;
        for (m = 0; m < arrayMsg.length; m++) {
            if (subString1[m] != null && subString1[m] != "") {
                arrayMsg[m] = subString1[m];
                continue;
            } else if (subString2[m] != null && subString2[m] != "") {
                arrayMsg[m] = subString2[m];
                continue;
            } else if (subString3[m] != null && subString3[m] != "") {
                arrayMsg[m] = subString3[m];
                continue;
            } else {
                throw new CalculationMessageException();
            }
        }
        return arrayMsg;
    }

    private static String buildMessage(String[] arrayMsg) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < arrayMsg.length; n++) {
            sb.append(arrayMsg[n]);
            if (n < arrayMsg.length - 1)
                sb.append(" ");
        }
        log.info("Msg final: " + sb.toString());
        return sb.toString();
    }
}
