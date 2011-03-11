package dataAnalysis;

/**
 * Represents a column in given data. This only represents flat data columns
 * such as gender/ age etc and not more complex types such as TagCloud
 * 
 * @author akanitkar
 */
enum Column
{
    RATING
    {
        public String toString()
        {
            return "RATING";
        }

        public int getValue()
        {
            return 0;
        }
    },
    TIMESTAMP
    {
        public String toString()
        {
            return "TIMESTAMP";
        }

        public int getValue()
        {
            return 1;
        }
    },
    AGE
    {
        public String toString()
        {
            return "AGE";
        }

        public int getValue()
        {
            return 2;
        }
    },
    GENDER
    {
        public String toString()
        {
            return "GENDER";
        }

        public int getValue()
        {
            return 3;
        }
    },
    CITY
    {
        public String toString()
        {
            return "CITY";
        }

        public int getValue()
        {
            return 4;
        }
    },
    STATE
    {
        public String toString()
        {
            return "STATE";
        }

        public int getValue()
        {
            return 5;
        }
    },
    COUNTRY
    {
        public String toString()
        {
            return "COUNTRY";
        }

        public int getValue()
        {
            return 6;
        }
    },
    SITE
    {
        public String toString()
        {
            return "SITE";
        }

        public int getValue()
        {
            return 7;
        }
    };

    abstract public int getValue();

}