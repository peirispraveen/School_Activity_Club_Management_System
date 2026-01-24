# Test Directory

This directory contains the JUnit test suite for the School Activity Club Management System.

## 📁 Structure

```
Test/
├── com/example/sacms/
│   ├── ClubValidatorTest.java    # Club validation tests
│   └── RegexTest.java            # Input validation regex tests
└── EventTestKit.java             # Event functionality test kit
```

## 🧪 Test Coverage

### ClubValidatorTest.java

Tests for club ID validation functionality:

#### Valid Club ID Tests
- ✅ **validCode1** - Tests valid format "C0001"
- ✅ **validCode2** - Tests valid format "C001"

#### Invalid Club ID Tests
- ❌ **inValidCode1** - Tests invalid format "C00007" (too long)
- ❌ **inValidCode2** - Tests invalid format "C005;" (contains semicolon)
- ❌ **inValidCode3** - Tests empty string

#### Additional Tests
- Club name validation
- Club description validation
- Maximum participants validation
- Date validation

### RegexTest.java

Tests for regular expression validation:

#### Email Validation
- Valid email formats
- Invalid email formats

#### Password Validation
- Valid password formats
- Invalid password formats

#### Other Validations
- Name validation
- ID validation
- Date format validation

### EventTestKit.java

Test utilities and helper methods for event testing:
- Event creation tests
- Event validation tests
- Event scheduling tests
- Event postponement tests


## 📊 Test Results

All tests should pass with:
- ✅ **Club Validation:** Multiple test cases
- ✅ **Regex Validation:** Email, password, and format tests
- ✅ **Event Tests:** Event functionality tests


## 📈 Test Metrics

### Coverage Areas
- Input validation
- Business logic validation
- Data format validation
- Edge cases and boundaries
- Error handling

### Test Types
- **Unit Tests** - Individual method testing
- **Validation Tests** - Input validation testing
- **Integration Tests** - Component interaction testing

---

**Note:** These tests ensure the robustness and reliability of the application's validation mechanisms. All tests should pass before deployment.
