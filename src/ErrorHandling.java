public class ErrorHandling {
   public static class OrganizationNotCreatedException extends Exception {
        public OrganizationNotCreatedException(String message) {
            super(message);
        }
    }

    public static class GroupNotFoundException extends Exception {
        public GroupNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidNameFormatException extends Exception {
        public InvalidNameFormatException(String message) {
            super(message);
        }
    }

    public static class PersonNotFoundException extends Exception {
        public PersonNotFoundException(String message) {
            super(message);
        }
    }
}
