import com.example.EventScheduling.EventValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventTestKit
{

    @Test
    void validateClubIDType()
    {
        String clubID = "C001";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertEquals(EventValidator.isValidClubID(), expectedOut);
    }

    @Test
    void validateClubIDLengthy()
    {
        String clubID = "C0001";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertNotEquals(EventValidator.isValidClubID(), expectedOut);
    }

    @Test
    void validateClubIDInt()
    {
        String clubID = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertNotEquals(EventValidator.isValidClubID(), expectedOut);
    }

    @Test
    void validateClubIDStr()
    {
        String clubID = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertNotEquals(EventValidator.isValidClubID(), expectedOut);
    }

    @Test
    void validateClubIDDouble()
    {
        String clubID = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertNotEquals(EventValidator.isValidClubID(), expectedOut);
    }
    @Test
    void validateClubIDEmpty()
    {
        String clubID = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateClubID(clubID);
        assertNotEquals(EventValidator.isValidClubID(), expectedOut);
    }

    @Test
    void validateEventIDType()
    {
        String eventID = "E001";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateEventIDLengthy()
    {
        String eventID = "E00000001";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertNotEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateEventIDInt()
    {
        String eventID = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertNotEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateEventIDDouble()
    {
        String eventID = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertNotEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateEventIDStr()
    {
        String eventID = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertNotEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateEventIDEmpty()
    {
        String eventID = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEventID(eventID);
        assertNotEquals(EventValidator.isValidEventID(), expectedOut);
    }

    @Test
    void validateYearIntValidRange()
    {
        String year = "2024";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertEquals(EventValidator.isValidYear(), expectedOut);
    }
    @Test
    void validateYearIntInvalidRange()
    {
        String year = "2000";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertNotEquals(EventValidator.isValidYear(), expectedOut);
    }
    @Test
    void validateYeaDoubleValidRange()
    {
        String year = "2024.12";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertNotEquals(EventValidator.isValidYear(), expectedOut);
    }
    @Test
    void validateYearDoubleInvalidRange()
    {
        String year = "2000.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertNotEquals(EventValidator.isValidYear(), expectedOut);
    }
    @Test
    void validateYearStr()
    {
        String year = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertNotEquals(EventValidator.isValidYear(), expectedOut);
    }
    @Test
    void validateYearEmpty()
    {
        String year = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        assertNotEquals(EventValidator.isValidYear(), expectedOut);
    }

    @Test
    void validateMonthIntValidRange()
    {
        String year = "";
        String month = "2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateMonthIntInvalidRange()
    {
        String year = "";
        String month = "13";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertNotEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateMonthDoubleValidRange()
    {
        String year = "";
        String month = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertNotEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateMonthDoubleInvalidRange()
    {
        String year = "";
        String month = "13.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertNotEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateMonthStr()
    {
        String year = "";
        String month = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertNotEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateMonthEmpty()
    {
        String year = "";
        String month = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year, month);
        assertNotEquals(EventValidator.isValidMonth(), expectedOut);
    }

    @Test
    void validateDayIntValidRange()
    {
        String year = "2024";
        String month = "1";
        String day = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayIntInvalidRange()
    {
        String year = "2024";
        String month = "1";
        String day = "100";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayDoubleValidRange()
    {
        String year = "2024";
        String month = "1";
        String day = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayDoubleInValidRange()
    {
        String year = "2024";
        String month = "1";
        String day = "13.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayStr()
    {
        String year = "2024";
        String month = "1";
        String day = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayEmpty()
    {
        String year = "2024";
        String month = "1";
        String day = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateDayInvalidYear()
    {
        String year = "str";
        String month = "1";
        String day = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }
    @Test
    void validateDayInvalidMonth()
    {
        String year = "2024";
        String month = "str";
        String day = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateDate(year);
        validationObj.validateDate(year, month);
        validationObj.validateDate(year, month, day);
        assertNotEquals(EventValidator.isValidDay(), expectedOut);
    }

    @Test
    void validateStartHourIntValidRange()
    {
        String startHour = "13";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartHourIntInvalidRange()
    {
        String startHour = "25";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertNotEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartHourDoubleValidRange()
    {
        String startHour = "13.2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertNotEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartHourDoubleInvalidRange()
    {
        String startHour = "25.2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertNotEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartHourStr()
    {
        String startHour = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertNotEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartHourEmpty()
    {
        String startHour = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartHour(startHour);
        assertNotEquals(EventValidator.isValidStartHour(), expectedOut);
    }

    @Test
    void validateStartMinIntValidRange()
    {
        String startMin = "12";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertEquals(EventValidator.isValidStartMin(), expectedOut);
    }

    @Test
    void validateStartMinIntInvalidRange()
    {
        String startMin = "61";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertNotEquals(EventValidator.isValidStartMin(), expectedOut);
    }

    @Test
    void validateStartMinDoubleValidRange()
    {
        String startMin = "12.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertNotEquals(EventValidator.isValidStartMin(), expectedOut);
    }

    @Test
    void validateStartMinDoubleInvalidRange()
    {
        String startMin = "61.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertNotEquals(EventValidator.isValidStartMin(), expectedOut);
    }

    @Test
    void validateStartMinStr()
    {
        String startMin = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertNotEquals(EventValidator.isValidStartMin(), expectedOut);
    }

    @Test
    void validateStartMinEmpty()
    {
        String startMin = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateStartMinute(startMin);
        assertNotEquals(EventValidator.isValidStartMin(), expectedOut);
    }

   @Test
    void validateEndHourIntValidRange()
    {
        String EndHour = "13";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(EndHour);
        assertEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateEndHourIntInvalidRange()
    {
        String endHour = "25";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(endHour);
        assertNotEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateStartEndDoubleValidRange()
    {
        String endHour = "13.2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(endHour);
        assertNotEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateEndHourDoubleInvalidRange()
    {
        String endHour = "25.2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(endHour);
        assertNotEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateEndHourStr()
    {
        String endHour = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(endHour);
        assertNotEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateEndHourEmpty()
    {
        String endHour = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndHour(endHour);
        assertNotEquals(EventValidator.isValidEndHour(), expectedOut);
    }

    @Test
    void validateEndMinIntValidRange()
    {
        String endMin = "12";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateEndMinIntInvalidRange()
    {
        String endMin = "61";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertNotEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateEndMinDoubleValidRange()
    {
        String endMin = "12.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertNotEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateEndMinDoubleInvalidRange()
    {
        String endMin = "61.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertNotEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateEndMinStr()
    {
        String endMin = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertNotEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateEndMinEmpty()
    {
        String endMin = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateEndMinute(endMin);
        assertNotEquals(EventValidator.isValidEndMin(), expectedOut);
    }

    @Test
    void validateNameStr()
    {
        String name = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateName(name);
        assertEquals(EventValidator.isValidName(), expectedOut);
    }

    @Test
    void validateNameInt()
    {
        String name = "2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateName(name);
        assertNotEquals(EventValidator.isValidName(), expectedOut);
    }
    @Test
    void validateNameDouble()
    {
        String name = "2.2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateName(name);
        assertNotEquals(EventValidator.isValidName(), expectedOut);
    }
    @Test
    void validateNameEmpty()
    {
        String name = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateName(name);
        assertNotEquals(EventValidator.isValidName(), expectedOut);
    }

    @Test
    void validatePlaceStr()
    {
        String place = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlace(place);
        assertEquals(EventValidator.isValidPlace(), expectedOut);
    }

    @Test
    void validatePlaceInt()
    {
        String place = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlace(place);
        assertNotEquals(EventValidator.isValidPlace(), expectedOut);
    }

    @Test
    void validatePlaceDouble()
    {
        String place = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlace(place);
        assertNotEquals(EventValidator.isValidPlace(), expectedOut);
    }

    @Test
    void validatePlaceEmpty()
    {
        String place = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlace(place);
        assertNotEquals(EventValidator.isValidPlace(), expectedOut);
    }

    @Test
    void validateTypeStr()
    {
        String type = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateType(type);
        assertEquals(EventValidator.isValidType(), expectedOut);
    }

    @Test
    void validateTypeInt()
    {
        String type = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateType(type);
        assertNotEquals(EventValidator.isValidType(), expectedOut);
    }

    @Test
    void validateTypeDouble()
    {
        String type = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateType(type);
        assertNotEquals(EventValidator.isValidType(), expectedOut);
    }

    @Test
    void validateTypeEmpty()
    {
        String type = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateType(type);
        assertNotEquals(EventValidator.isValidType(), expectedOut);
    }

    @Test
    void validatePlatformStr()
    {
        String platform = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlatform(platform);
        assertEquals(EventValidator.isValidPlatform(), expectedOut);
    }

    @Test
    void validatePlatformInt()
    {
        String platform = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlatform(platform);
        assertNotEquals(EventValidator.isValidPlatform(), expectedOut);
    }

    @Test
    void validatePlatformDouble()
    {
        String platform = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlatform(platform);
        assertNotEquals(EventValidator.isValidPlatform(), expectedOut);
    }
    @Test
    void validatePlatformEmpty()
    {
        String platform = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validatePlatform(platform);
        assertNotEquals(EventValidator.isValidPlatform(), expectedOut);
    }

    @Test
    void validateActivityNoIntValidRange()
    {
        String no = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertEquals(EventValidator.isValidActivityNo(), expectedOut);
    }

    @Test
    void validateActivityNoIntInvalidRange()
    {
        String no = "-2";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertNotEquals(EventValidator.isValidActivityNo(), expectedOut);
    }

    @Test
    void validateActivityNoDoubleValidRange()
    {
        String no = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertNotEquals(EventValidator.isValidActivityNo(), expectedOut);
    }

    @Test
    void validateActivityNoDoubleInvalidRange()
    {
        String no = "-2.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertNotEquals(EventValidator.isValidActivityNo(), expectedOut);
    }

    @Test
    void validateActivityNoStr()
    {
        String no = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertNotEquals(EventValidator.isValidActivityNo(), expectedOut);
    }
    @Test
    void validateActivityNoEmpty()
    {
        String no = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateActivityNo(no);
        assertNotEquals(EventValidator.isValidActivityNo(), expectedOut);
    }

    @Test
    void validateLink()
    {
        String link = "https://zoom.us";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateLink(link);
        assertEquals(EventValidator.isValidLink(), expectedOut);
    }

    @Test
    void validateLinkStr()
    {
        String link = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateLink(link);
        assertNotEquals(EventValidator.isValidLink(), expectedOut);
    }

    @Test
    void validateLinkInt()
    {
        String link = "1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateLink(link);
        assertNotEquals(EventValidator.isValidLink(), expectedOut);
    }
    @Test
    void validateLinkDouble()
    {
        String link = "1.1";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateLink(link);
        assertNotEquals(EventValidator.isValidLink(), expectedOut);
    }
    @Test
    void validateLinkEmpty()
    {
        String link = "";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        validationObj.validateLink(link);
        assertNotEquals(EventValidator.isValidLink(), expectedOut);
    }

    @Test
    void validateInt()
    {
        String integer = "1";
        boolean expectedPut = true;
        EventValidator validationObj = new EventValidator();
        assertEquals(validationObj.validateInt(integer), expectedPut);
    }

    @Test
    void validateIntDouble()
    {
        String integer = "1.1";
        boolean expectedPut = true;
        EventValidator validationObj = new EventValidator();
        assertNotEquals(validationObj.validateInt(integer), expectedPut);
    }

     @Test
    void validateIntStr()
    {
        String integer = "str";
        boolean expectedPut = true;
        EventValidator validationObj = new EventValidator();
        assertNotEquals(validationObj.validateInt(integer), expectedPut);
    }

     @Test
    void validateIntEmpty()
    {
        String integer = "";
        boolean expectedPut = true;
        EventValidator validationObj = new EventValidator();
        assertNotEquals(validationObj.validateInt(integer), expectedPut);
    }

    @Test
    void validateStr()
    {
        String str = "str";
        boolean expectedOut = true;
        EventValidator validationObj = new EventValidator();
        assertEquals(validationObj.validateString(str), expectedOut);
    }

}
