package gr.nikolis.JTapi.constants;

/**
 * The fields of this interface contains indices of various columns and total
 * number of columns of the table representing call data in the GUI and the
 * table representing directory lookup results. Classes Click2Call,
 * Click2CallGUI, DirectoryLookupGUI and DisplayParser implements this
 * interface.
 *
 * @author Avaya
 */

public interface TableConstants {

    /**
     * Constants for table displaying call log.
     */
    interface CallLogTableConstants {
        // Index of the column displaying caller Id of received call.
        int NAME_COLUMN_INDEX = 0;

        // Index of the column displaying phone number of caller.
        int NUMBER_COLUMN_INDEX = 1;

        // Index of the column displaying time of received call.
        int TIME_COLUMN_INDEX = 2;

        // Index of the column displaying status (answered/missed) of received
        // call.
        int STATUS_COLUMN_INDEX = 3;

        // Total number of columns in table displaying received calls.
        int TABLE_COLUMN_COUNT = 4;
    }

    /**
     * Constants for table displaying directory lookup results.
     */
    interface DirTableConstants {
        // Index of the column displaying name of the person searched for.
        int NAME_COLUMN_INDEX = 0;

        // Index of the column displaying phone number of the person searched
        // for.
        int NUMBER_COLUMN_INDEX = 1;

        // Index of the column displaying mail id of the person searched for.
        int MAIL_COLUMN_INDEX = 2;

        // Total number of columns in table displaying directory lookup
        // results.
        int TABLE_COLUMN_COUNT = 3;
    }
}