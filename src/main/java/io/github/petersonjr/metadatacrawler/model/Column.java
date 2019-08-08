/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.github.petersonjr.metadatacrawler.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** A table column */
@org.apache.avro.specific.AvroGenerated
public class Column extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2298193050281578265L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Column\",\"namespace\":\"io.github.petersonjr.metadatacrawler.model\",\"doc\":\"A table column\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"The column name\"},{\"name\":\"qualified_name\",\"type\":[\"null\",\"string\"],\"doc\":\"The column's qualified name\",\"default\":null},{\"name\":\"number\",\"type\":[\"null\",\"int\"],\"doc\":\"The column number (0-based)\",\"default\":null},{\"name\":\"size\",\"type\":[\"null\",\"int\"],\"doc\":\"The column size\",\"default\":null},{\"name\":\"nullable\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column nullable?\",\"default\":null},{\"name\":\"primary_key\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column a primary key?\",\"default\":null},{\"name\":\"indexed\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column indexed?\",\"default\":null},{\"name\":\"column_type\",\"type\":[\"null\",\"string\"],\"doc\":\"The column type\",\"default\":null},{\"name\":\"native_type\",\"type\":[\"null\",\"string\"],\"doc\":\"The native column type (as defined by the datasource itself)\",\"default\":null},{\"name\":\"remarks\",\"type\":[\"null\",\"string\"],\"doc\":\"Any remarks on the column\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Column> ENCODER =
      new BinaryMessageEncoder<Column>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Column> DECODER =
      new BinaryMessageDecoder<Column>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Column> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Column> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Column>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Column to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Column from a ByteBuffer. */
  public static Column fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** The column name */
   private java.lang.CharSequence name;
  /** The column's qualified name */
   private java.lang.CharSequence qualified_name;
  /** The column number (0-based) */
   private java.lang.Integer number;
  /** The column size */
   private java.lang.Integer size;
  /** Is the column nullable? */
   private java.lang.Boolean nullable;
  /** Is the column a primary key? */
   private java.lang.Boolean primary_key;
  /** Is the column indexed? */
   private java.lang.Boolean indexed;
  /** The column type */
   private java.lang.CharSequence column_type;
  /** The native column type (as defined by the datasource itself) */
   private java.lang.CharSequence native_type;
  /** Any remarks on the column */
   private java.lang.CharSequence remarks;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Column() {}

  /**
   * All-args constructor.
   * @param name The column name
   * @param qualified_name The column's qualified name
   * @param number The column number (0-based)
   * @param size The column size
   * @param nullable Is the column nullable?
   * @param primary_key Is the column a primary key?
   * @param indexed Is the column indexed?
   * @param column_type The column type
   * @param native_type The native column type (as defined by the datasource itself)
   * @param remarks Any remarks on the column
   */
  public Column(java.lang.CharSequence name, java.lang.CharSequence qualified_name, java.lang.Integer number, java.lang.Integer size, java.lang.Boolean nullable, java.lang.Boolean primary_key, java.lang.Boolean indexed, java.lang.CharSequence column_type, java.lang.CharSequence native_type, java.lang.CharSequence remarks) {
    this.name = name;
    this.qualified_name = qualified_name;
    this.number = number;
    this.size = size;
    this.nullable = nullable;
    this.primary_key = primary_key;
    this.indexed = indexed;
    this.column_type = column_type;
    this.native_type = native_type;
    this.remarks = remarks;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return qualified_name;
    case 2: return number;
    case 3: return size;
    case 4: return nullable;
    case 5: return primary_key;
    case 6: return indexed;
    case 7: return column_type;
    case 8: return native_type;
    case 9: return remarks;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: qualified_name = (java.lang.CharSequence)value$; break;
    case 2: number = (java.lang.Integer)value$; break;
    case 3: size = (java.lang.Integer)value$; break;
    case 4: nullable = (java.lang.Boolean)value$; break;
    case 5: primary_key = (java.lang.Boolean)value$; break;
    case 6: indexed = (java.lang.Boolean)value$; break;
    case 7: column_type = (java.lang.CharSequence)value$; break;
    case 8: native_type = (java.lang.CharSequence)value$; break;
    case 9: remarks = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return The column name
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * The column name
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'qualified_name' field.
   * @return The column's qualified name
   */
  public java.lang.CharSequence getQualifiedName() {
    return qualified_name;
  }

  /**
   * Sets the value of the 'qualified_name' field.
   * The column's qualified name
   * @param value the value to set.
   */
  public void setQualifiedName(java.lang.CharSequence value) {
    this.qualified_name = value;
  }

  /**
   * Gets the value of the 'number' field.
   * @return The column number (0-based)
   */
  public java.lang.Integer getNumber() {
    return number;
  }

  /**
   * Sets the value of the 'number' field.
   * The column number (0-based)
   * @param value the value to set.
   */
  public void setNumber(java.lang.Integer value) {
    this.number = value;
  }

  /**
   * Gets the value of the 'size' field.
   * @return The column size
   */
  public java.lang.Integer getSize() {
    return size;
  }

  /**
   * Sets the value of the 'size' field.
   * The column size
   * @param value the value to set.
   */
  public void setSize(java.lang.Integer value) {
    this.size = value;
  }

  /**
   * Gets the value of the 'nullable' field.
   * @return Is the column nullable?
   */
  public java.lang.Boolean getNullable() {
    return nullable;
  }

  /**
   * Sets the value of the 'nullable' field.
   * Is the column nullable?
   * @param value the value to set.
   */
  public void setNullable(java.lang.Boolean value) {
    this.nullable = value;
  }

  /**
   * Gets the value of the 'primary_key' field.
   * @return Is the column a primary key?
   */
  public java.lang.Boolean getPrimaryKey() {
    return primary_key;
  }

  /**
   * Sets the value of the 'primary_key' field.
   * Is the column a primary key?
   * @param value the value to set.
   */
  public void setPrimaryKey(java.lang.Boolean value) {
    this.primary_key = value;
  }

  /**
   * Gets the value of the 'indexed' field.
   * @return Is the column indexed?
   */
  public java.lang.Boolean getIndexed() {
    return indexed;
  }

  /**
   * Sets the value of the 'indexed' field.
   * Is the column indexed?
   * @param value the value to set.
   */
  public void setIndexed(java.lang.Boolean value) {
    this.indexed = value;
  }

  /**
   * Gets the value of the 'column_type' field.
   * @return The column type
   */
  public java.lang.CharSequence getColumnType() {
    return column_type;
  }

  /**
   * Sets the value of the 'column_type' field.
   * The column type
   * @param value the value to set.
   */
  public void setColumnType(java.lang.CharSequence value) {
    this.column_type = value;
  }

  /**
   * Gets the value of the 'native_type' field.
   * @return The native column type (as defined by the datasource itself)
   */
  public java.lang.CharSequence getNativeType() {
    return native_type;
  }

  /**
   * Sets the value of the 'native_type' field.
   * The native column type (as defined by the datasource itself)
   * @param value the value to set.
   */
  public void setNativeType(java.lang.CharSequence value) {
    this.native_type = value;
  }

  /**
   * Gets the value of the 'remarks' field.
   * @return Any remarks on the column
   */
  public java.lang.CharSequence getRemarks() {
    return remarks;
  }

  /**
   * Sets the value of the 'remarks' field.
   * Any remarks on the column
   * @param value the value to set.
   */
  public void setRemarks(java.lang.CharSequence value) {
    this.remarks = value;
  }

  /**
   * Creates a new Column RecordBuilder.
   * @return A new Column RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Column.Builder newBuilder() {
    return new io.github.petersonjr.metadatacrawler.model.Column.Builder();
  }

  /**
   * Creates a new Column RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Column RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Column.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Column.Builder other) {
    return new io.github.petersonjr.metadatacrawler.model.Column.Builder(other);
  }

  /**
   * Creates a new Column RecordBuilder by copying an existing Column instance.
   * @param other The existing instance to copy.
   * @return A new Column RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Column.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Column other) {
    return new io.github.petersonjr.metadatacrawler.model.Column.Builder(other);
  }

  /**
   * RecordBuilder for Column instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Column>
    implements org.apache.avro.data.RecordBuilder<Column> {

    /** The column name */
    private java.lang.CharSequence name;
    /** The column's qualified name */
    private java.lang.CharSequence qualified_name;
    /** The column number (0-based) */
    private java.lang.Integer number;
    /** The column size */
    private java.lang.Integer size;
    /** Is the column nullable? */
    private java.lang.Boolean nullable;
    /** Is the column a primary key? */
    private java.lang.Boolean primary_key;
    /** Is the column indexed? */
    private java.lang.Boolean indexed;
    /** The column type */
    private java.lang.CharSequence column_type;
    /** The native column type (as defined by the datasource itself) */
    private java.lang.CharSequence native_type;
    /** Any remarks on the column */
    private java.lang.CharSequence remarks;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Column.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.qualified_name)) {
        this.qualified_name = data().deepCopy(fields()[1].schema(), other.qualified_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.number)) {
        this.number = data().deepCopy(fields()[2].schema(), other.number);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.size)) {
        this.size = data().deepCopy(fields()[3].schema(), other.size);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.nullable)) {
        this.nullable = data().deepCopy(fields()[4].schema(), other.nullable);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.primary_key)) {
        this.primary_key = data().deepCopy(fields()[5].schema(), other.primary_key);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.indexed)) {
        this.indexed = data().deepCopy(fields()[6].schema(), other.indexed);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.column_type)) {
        this.column_type = data().deepCopy(fields()[7].schema(), other.column_type);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.native_type)) {
        this.native_type = data().deepCopy(fields()[8].schema(), other.native_type);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.remarks)) {
        this.remarks = data().deepCopy(fields()[9].schema(), other.remarks);
        fieldSetFlags()[9] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Column instance
     * @param other The existing instance to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Column other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.qualified_name)) {
        this.qualified_name = data().deepCopy(fields()[1].schema(), other.qualified_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.number)) {
        this.number = data().deepCopy(fields()[2].schema(), other.number);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.size)) {
        this.size = data().deepCopy(fields()[3].schema(), other.size);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.nullable)) {
        this.nullable = data().deepCopy(fields()[4].schema(), other.nullable);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.primary_key)) {
        this.primary_key = data().deepCopy(fields()[5].schema(), other.primary_key);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.indexed)) {
        this.indexed = data().deepCopy(fields()[6].schema(), other.indexed);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.column_type)) {
        this.column_type = data().deepCopy(fields()[7].schema(), other.column_type);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.native_type)) {
        this.native_type = data().deepCopy(fields()[8].schema(), other.native_type);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.remarks)) {
        this.remarks = data().deepCopy(fields()[9].schema(), other.remarks);
        fieldSetFlags()[9] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * The column name
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * The column name
      * @param value The value of 'name'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * The column name
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * The column name
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'qualified_name' field.
      * The column's qualified name
      * @return The value.
      */
    public java.lang.CharSequence getQualifiedName() {
      return qualified_name;
    }

    /**
      * Sets the value of the 'qualified_name' field.
      * The column's qualified name
      * @param value The value of 'qualified_name'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setQualifiedName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.qualified_name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'qualified_name' field has been set.
      * The column's qualified name
      * @return True if the 'qualified_name' field has been set, false otherwise.
      */
    public boolean hasQualifiedName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'qualified_name' field.
      * The column's qualified name
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearQualifiedName() {
      qualified_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'number' field.
      * The column number (0-based)
      * @return The value.
      */
    public java.lang.Integer getNumber() {
      return number;
    }

    /**
      * Sets the value of the 'number' field.
      * The column number (0-based)
      * @param value The value of 'number'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setNumber(java.lang.Integer value) {
      validate(fields()[2], value);
      this.number = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'number' field has been set.
      * The column number (0-based)
      * @return True if the 'number' field has been set, false otherwise.
      */
    public boolean hasNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'number' field.
      * The column number (0-based)
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearNumber() {
      number = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'size' field.
      * The column size
      * @return The value.
      */
    public java.lang.Integer getSize() {
      return size;
    }

    /**
      * Sets the value of the 'size' field.
      * The column size
      * @param value The value of 'size'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setSize(java.lang.Integer value) {
      validate(fields()[3], value);
      this.size = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'size' field has been set.
      * The column size
      * @return True if the 'size' field has been set, false otherwise.
      */
    public boolean hasSize() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'size' field.
      * The column size
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearSize() {
      size = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'nullable' field.
      * Is the column nullable?
      * @return The value.
      */
    public java.lang.Boolean getNullable() {
      return nullable;
    }

    /**
      * Sets the value of the 'nullable' field.
      * Is the column nullable?
      * @param value The value of 'nullable'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setNullable(java.lang.Boolean value) {
      validate(fields()[4], value);
      this.nullable = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'nullable' field has been set.
      * Is the column nullable?
      * @return True if the 'nullable' field has been set, false otherwise.
      */
    public boolean hasNullable() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'nullable' field.
      * Is the column nullable?
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearNullable() {
      nullable = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'primary_key' field.
      * Is the column a primary key?
      * @return The value.
      */
    public java.lang.Boolean getPrimaryKey() {
      return primary_key;
    }

    /**
      * Sets the value of the 'primary_key' field.
      * Is the column a primary key?
      * @param value The value of 'primary_key'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setPrimaryKey(java.lang.Boolean value) {
      validate(fields()[5], value);
      this.primary_key = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'primary_key' field has been set.
      * Is the column a primary key?
      * @return True if the 'primary_key' field has been set, false otherwise.
      */
    public boolean hasPrimaryKey() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'primary_key' field.
      * Is the column a primary key?
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearPrimaryKey() {
      primary_key = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'indexed' field.
      * Is the column indexed?
      * @return The value.
      */
    public java.lang.Boolean getIndexed() {
      return indexed;
    }

    /**
      * Sets the value of the 'indexed' field.
      * Is the column indexed?
      * @param value The value of 'indexed'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setIndexed(java.lang.Boolean value) {
      validate(fields()[6], value);
      this.indexed = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'indexed' field has been set.
      * Is the column indexed?
      * @return True if the 'indexed' field has been set, false otherwise.
      */
    public boolean hasIndexed() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'indexed' field.
      * Is the column indexed?
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearIndexed() {
      indexed = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'column_type' field.
      * The column type
      * @return The value.
      */
    public java.lang.CharSequence getColumnType() {
      return column_type;
    }

    /**
      * Sets the value of the 'column_type' field.
      * The column type
      * @param value The value of 'column_type'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setColumnType(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.column_type = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'column_type' field has been set.
      * The column type
      * @return True if the 'column_type' field has been set, false otherwise.
      */
    public boolean hasColumnType() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'column_type' field.
      * The column type
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearColumnType() {
      column_type = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'native_type' field.
      * The native column type (as defined by the datasource itself)
      * @return The value.
      */
    public java.lang.CharSequence getNativeType() {
      return native_type;
    }

    /**
      * Sets the value of the 'native_type' field.
      * The native column type (as defined by the datasource itself)
      * @param value The value of 'native_type'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setNativeType(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.native_type = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'native_type' field has been set.
      * The native column type (as defined by the datasource itself)
      * @return True if the 'native_type' field has been set, false otherwise.
      */
    public boolean hasNativeType() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'native_type' field.
      * The native column type (as defined by the datasource itself)
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearNativeType() {
      native_type = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'remarks' field.
      * Any remarks on the column
      * @return The value.
      */
    public java.lang.CharSequence getRemarks() {
      return remarks;
    }

    /**
      * Sets the value of the 'remarks' field.
      * Any remarks on the column
      * @param value The value of 'remarks'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder setRemarks(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.remarks = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'remarks' field has been set.
      * Any remarks on the column
      * @return True if the 'remarks' field has been set, false otherwise.
      */
    public boolean hasRemarks() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'remarks' field.
      * Any remarks on the column
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Column.Builder clearRemarks() {
      remarks = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Column build() {
      try {
        Column record = new Column();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.qualified_name = fieldSetFlags()[1] ? this.qualified_name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.number = fieldSetFlags()[2] ? this.number : (java.lang.Integer) defaultValue(fields()[2]);
        record.size = fieldSetFlags()[3] ? this.size : (java.lang.Integer) defaultValue(fields()[3]);
        record.nullable = fieldSetFlags()[4] ? this.nullable : (java.lang.Boolean) defaultValue(fields()[4]);
        record.primary_key = fieldSetFlags()[5] ? this.primary_key : (java.lang.Boolean) defaultValue(fields()[5]);
        record.indexed = fieldSetFlags()[6] ? this.indexed : (java.lang.Boolean) defaultValue(fields()[6]);
        record.column_type = fieldSetFlags()[7] ? this.column_type : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.native_type = fieldSetFlags()[8] ? this.native_type : (java.lang.CharSequence) defaultValue(fields()[8]);
        record.remarks = fieldSetFlags()[9] ? this.remarks : (java.lang.CharSequence) defaultValue(fields()[9]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Column>
    WRITER$ = (org.apache.avro.io.DatumWriter<Column>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Column>
    READER$ = (org.apache.avro.io.DatumReader<Column>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}