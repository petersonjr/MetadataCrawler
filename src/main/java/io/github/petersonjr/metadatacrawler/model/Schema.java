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
/** A catalog's schema */
@org.apache.avro.specific.AvroGenerated
public class Schema extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 905629792458108047L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Schema\",\"namespace\":\"io.github.petersonjr.metadatacrawler.model\",\"doc\":\"A catalog's schema\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"The schema name\"},{\"name\":\"qualified_name\",\"type\":[\"null\",\"string\"],\"doc\":\"The schemas='s qualified name\",\"default\":null},{\"name\":\"tables\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Table\",\"doc\":\"A table, which usually belongs to a schema and has columns\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"The table name\"},{\"name\":\"type\",\"type\":[\"null\",\"string\"],\"doc\":\"The table type (VIEW, TABLE, etc)\",\"default\":null},{\"name\":\"qualified_name\",\"type\":[\"null\",\"string\"],\"doc\":\"The table's qualified name\",\"default\":null},{\"name\":\"remarks\",\"type\":[\"null\",\"string\"],\"doc\":\"Any remarks on the table\",\"default\":null},{\"name\":\"columns\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Column\",\"doc\":\"A table column\",\"fields\":[{\"name\":\"name\",\"type\":\"string\",\"doc\":\"The column name\"},{\"name\":\"qualified_name\",\"type\":[\"null\",\"string\"],\"doc\":\"The column's qualified name\",\"default\":null},{\"name\":\"number\",\"type\":[\"null\",\"int\"],\"doc\":\"The column number (0-based)\",\"default\":null},{\"name\":\"size\",\"type\":[\"null\",\"int\"],\"doc\":\"The column size\",\"default\":null},{\"name\":\"nullable\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column nullable?\",\"default\":null},{\"name\":\"primary_key\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column a primary key?\",\"default\":null},{\"name\":\"indexed\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Is the column indexed?\",\"default\":null},{\"name\":\"column_type\",\"type\":[\"null\",\"string\"],\"doc\":\"The column type\",\"default\":null},{\"name\":\"native_type\",\"type\":[\"null\",\"string\"],\"doc\":\"The native column type (as defined by the datasource itself)\",\"default\":null},{\"name\":\"remarks\",\"type\":[\"null\",\"string\"],\"doc\":\"Any remarks on the column\",\"default\":null}]}}],\"doc\":\"The table's columns\",\"default\":null}]}}],\"doc\":\"The schemas' tables\",\"default\":null},{\"name\":\"relationships\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Relationship\",\"doc\":\"Relationship between two tables, where one set of columns is the primary key, and another set is the foreign key\",\"fields\":[{\"name\":\"primary_table\",\"type\":\"string\",\"doc\":\"The table of the primary key column(s)\"},{\"name\":\"primary_columns\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"The primary key columns of this relationship\",\"default\":[]},{\"name\":\"foreign_table\",\"type\":\"string\",\"doc\":\"The table of the foreign key column(s).\"},{\"name\":\"foreign_columns\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"The foreign key columns of this relationship\",\"default\":[]}]}}],\"doc\":\"The schemas' tables relationships\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Schema> ENCODER =
      new BinaryMessageEncoder<Schema>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Schema> DECODER =
      new BinaryMessageDecoder<Schema>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Schema> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Schema> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Schema>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Schema to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Schema from a ByteBuffer. */
  public static Schema fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** The schema name */
   private java.lang.CharSequence name;
  /** The schemas='s qualified name */
   private java.lang.CharSequence qualified_name;
  /** The schemas' tables */
   private java.util.List<io.github.petersonjr.metadatacrawler.model.Table> tables;
  /** The schemas' tables relationships */
   private java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> relationships;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Schema() {}

  /**
   * All-args constructor.
   * @param name The schema name
   * @param qualified_name The schemas='s qualified name
   * @param tables The schemas' tables
   * @param relationships The schemas' tables relationships
   */
  public Schema(java.lang.CharSequence name, java.lang.CharSequence qualified_name, java.util.List<io.github.petersonjr.metadatacrawler.model.Table> tables, java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> relationships) {
    this.name = name;
    this.qualified_name = qualified_name;
    this.tables = tables;
    this.relationships = relationships;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return qualified_name;
    case 2: return tables;
    case 3: return relationships;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.CharSequence)value$; break;
    case 1: qualified_name = (java.lang.CharSequence)value$; break;
    case 2: tables = (java.util.List<io.github.petersonjr.metadatacrawler.model.Table>)value$; break;
    case 3: relationships = (java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return The schema name
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * The schema name
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'qualified_name' field.
   * @return The schemas='s qualified name
   */
  public java.lang.CharSequence getQualifiedName() {
    return qualified_name;
  }

  /**
   * Sets the value of the 'qualified_name' field.
   * The schemas='s qualified name
   * @param value the value to set.
   */
  public void setQualifiedName(java.lang.CharSequence value) {
    this.qualified_name = value;
  }

  /**
   * Gets the value of the 'tables' field.
   * @return The schemas' tables
   */
  public java.util.List<io.github.petersonjr.metadatacrawler.model.Table> getTables() {
    return tables;
  }

  /**
   * Sets the value of the 'tables' field.
   * The schemas' tables
   * @param value the value to set.
   */
  public void setTables(java.util.List<io.github.petersonjr.metadatacrawler.model.Table> value) {
    this.tables = value;
  }

  /**
   * Gets the value of the 'relationships' field.
   * @return The schemas' tables relationships
   */
  public java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> getRelationships() {
    return relationships;
  }

  /**
   * Sets the value of the 'relationships' field.
   * The schemas' tables relationships
   * @param value the value to set.
   */
  public void setRelationships(java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> value) {
    this.relationships = value;
  }

  /**
   * Creates a new Schema RecordBuilder.
   * @return A new Schema RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Schema.Builder newBuilder() {
    return new io.github.petersonjr.metadatacrawler.model.Schema.Builder();
  }

  /**
   * Creates a new Schema RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Schema RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Schema.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Schema.Builder other) {
    return new io.github.petersonjr.metadatacrawler.model.Schema.Builder(other);
  }

  /**
   * Creates a new Schema RecordBuilder by copying an existing Schema instance.
   * @param other The existing instance to copy.
   * @return A new Schema RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Schema.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Schema other) {
    return new io.github.petersonjr.metadatacrawler.model.Schema.Builder(other);
  }

  /**
   * RecordBuilder for Schema instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Schema>
    implements org.apache.avro.data.RecordBuilder<Schema> {

    /** The schema name */
    private java.lang.CharSequence name;
    /** The schemas='s qualified name */
    private java.lang.CharSequence qualified_name;
    /** The schemas' tables */
    private java.util.List<io.github.petersonjr.metadatacrawler.model.Table> tables;
    /** The schemas' tables relationships */
    private java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> relationships;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Schema.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.qualified_name)) {
        this.qualified_name = data().deepCopy(fields()[1].schema(), other.qualified_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.tables)) {
        this.tables = data().deepCopy(fields()[2].schema(), other.tables);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.relationships)) {
        this.relationships = data().deepCopy(fields()[3].schema(), other.relationships);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Schema instance
     * @param other The existing instance to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Schema other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.qualified_name)) {
        this.qualified_name = data().deepCopy(fields()[1].schema(), other.qualified_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.tables)) {
        this.tables = data().deepCopy(fields()[2].schema(), other.tables);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.relationships)) {
        this.relationships = data().deepCopy(fields()[3].schema(), other.relationships);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * The schema name
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * The schema name
      * @param value The value of 'name'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * The schema name
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * The schema name
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'qualified_name' field.
      * The schemas='s qualified name
      * @return The value.
      */
    public java.lang.CharSequence getQualifiedName() {
      return qualified_name;
    }

    /**
      * Sets the value of the 'qualified_name' field.
      * The schemas='s qualified name
      * @param value The value of 'qualified_name'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder setQualifiedName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.qualified_name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'qualified_name' field has been set.
      * The schemas='s qualified name
      * @return True if the 'qualified_name' field has been set, false otherwise.
      */
    public boolean hasQualifiedName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'qualified_name' field.
      * The schemas='s qualified name
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder clearQualifiedName() {
      qualified_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'tables' field.
      * The schemas' tables
      * @return The value.
      */
    public java.util.List<io.github.petersonjr.metadatacrawler.model.Table> getTables() {
      return tables;
    }

    /**
      * Sets the value of the 'tables' field.
      * The schemas' tables
      * @param value The value of 'tables'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder setTables(java.util.List<io.github.petersonjr.metadatacrawler.model.Table> value) {
      validate(fields()[2], value);
      this.tables = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'tables' field has been set.
      * The schemas' tables
      * @return True if the 'tables' field has been set, false otherwise.
      */
    public boolean hasTables() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'tables' field.
      * The schemas' tables
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder clearTables() {
      tables = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'relationships' field.
      * The schemas' tables relationships
      * @return The value.
      */
    public java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> getRelationships() {
      return relationships;
    }

    /**
      * Sets the value of the 'relationships' field.
      * The schemas' tables relationships
      * @param value The value of 'relationships'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder setRelationships(java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship> value) {
      validate(fields()[3], value);
      this.relationships = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'relationships' field has been set.
      * The schemas' tables relationships
      * @return True if the 'relationships' field has been set, false otherwise.
      */
    public boolean hasRelationships() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'relationships' field.
      * The schemas' tables relationships
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Schema.Builder clearRelationships() {
      relationships = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Schema build() {
      try {
        Schema record = new Schema();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.qualified_name = fieldSetFlags()[1] ? this.qualified_name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.tables = fieldSetFlags()[2] ? this.tables : (java.util.List<io.github.petersonjr.metadatacrawler.model.Table>) defaultValue(fields()[2]);
        record.relationships = fieldSetFlags()[3] ? this.relationships : (java.util.List<io.github.petersonjr.metadatacrawler.model.Relationship>) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Schema>
    WRITER$ = (org.apache.avro.io.DatumWriter<Schema>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Schema>
    READER$ = (org.apache.avro.io.DatumReader<Schema>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
