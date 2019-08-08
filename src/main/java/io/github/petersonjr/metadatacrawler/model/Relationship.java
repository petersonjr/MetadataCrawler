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
/** Relationship between two tables, where one set of columns is the primary key, and another set is the foreign key */
@org.apache.avro.specific.AvroGenerated
public class Relationship extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8226444601488357604L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Relationship\",\"namespace\":\"io.github.petersonjr.metadatacrawler.model\",\"doc\":\"Relationship between two tables, where one set of columns is the primary key, and another set is the foreign key\",\"fields\":[{\"name\":\"primary_table\",\"type\":\"string\",\"doc\":\"The table of the primary key column(s)\"},{\"name\":\"primary_columns\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"The primary key columns of this relationship\",\"default\":[]},{\"name\":\"foreign_table\",\"type\":\"string\",\"doc\":\"The table of the foreign key column(s).\"},{\"name\":\"foreign_columns\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"The foreign key columns of this relationship\",\"default\":[]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Relationship> ENCODER =
      new BinaryMessageEncoder<Relationship>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Relationship> DECODER =
      new BinaryMessageDecoder<Relationship>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Relationship> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Relationship> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Relationship>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Relationship to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Relationship from a ByteBuffer. */
  public static Relationship fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** The table of the primary key column(s) */
   private java.lang.CharSequence primary_table;
  /** The primary key columns of this relationship */
   private java.util.List<java.lang.CharSequence> primary_columns;
  /** The table of the foreign key column(s). */
   private java.lang.CharSequence foreign_table;
  /** The foreign key columns of this relationship */
   private java.util.List<java.lang.CharSequence> foreign_columns;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Relationship() {}

  /**
   * All-args constructor.
   * @param primary_table The table of the primary key column(s)
   * @param primary_columns The primary key columns of this relationship
   * @param foreign_table The table of the foreign key column(s).
   * @param foreign_columns The foreign key columns of this relationship
   */
  public Relationship(java.lang.CharSequence primary_table, java.util.List<java.lang.CharSequence> primary_columns, java.lang.CharSequence foreign_table, java.util.List<java.lang.CharSequence> foreign_columns) {
    this.primary_table = primary_table;
    this.primary_columns = primary_columns;
    this.foreign_table = foreign_table;
    this.foreign_columns = foreign_columns;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return primary_table;
    case 1: return primary_columns;
    case 2: return foreign_table;
    case 3: return foreign_columns;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: primary_table = (java.lang.CharSequence)value$; break;
    case 1: primary_columns = (java.util.List<java.lang.CharSequence>)value$; break;
    case 2: foreign_table = (java.lang.CharSequence)value$; break;
    case 3: foreign_columns = (java.util.List<java.lang.CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'primary_table' field.
   * @return The table of the primary key column(s)
   */
  public java.lang.CharSequence getPrimaryTable() {
    return primary_table;
  }

  /**
   * Sets the value of the 'primary_table' field.
   * The table of the primary key column(s)
   * @param value the value to set.
   */
  public void setPrimaryTable(java.lang.CharSequence value) {
    this.primary_table = value;
  }

  /**
   * Gets the value of the 'primary_columns' field.
   * @return The primary key columns of this relationship
   */
  public java.util.List<java.lang.CharSequence> getPrimaryColumns() {
    return primary_columns;
  }

  /**
   * Sets the value of the 'primary_columns' field.
   * The primary key columns of this relationship
   * @param value the value to set.
   */
  public void setPrimaryColumns(java.util.List<java.lang.CharSequence> value) {
    this.primary_columns = value;
  }

  /**
   * Gets the value of the 'foreign_table' field.
   * @return The table of the foreign key column(s).
   */
  public java.lang.CharSequence getForeignTable() {
    return foreign_table;
  }

  /**
   * Sets the value of the 'foreign_table' field.
   * The table of the foreign key column(s).
   * @param value the value to set.
   */
  public void setForeignTable(java.lang.CharSequence value) {
    this.foreign_table = value;
  }

  /**
   * Gets the value of the 'foreign_columns' field.
   * @return The foreign key columns of this relationship
   */
  public java.util.List<java.lang.CharSequence> getForeignColumns() {
    return foreign_columns;
  }

  /**
   * Sets the value of the 'foreign_columns' field.
   * The foreign key columns of this relationship
   * @param value the value to set.
   */
  public void setForeignColumns(java.util.List<java.lang.CharSequence> value) {
    this.foreign_columns = value;
  }

  /**
   * Creates a new Relationship RecordBuilder.
   * @return A new Relationship RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Relationship.Builder newBuilder() {
    return new io.github.petersonjr.metadatacrawler.model.Relationship.Builder();
  }

  /**
   * Creates a new Relationship RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Relationship RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Relationship.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Relationship.Builder other) {
    return new io.github.petersonjr.metadatacrawler.model.Relationship.Builder(other);
  }

  /**
   * Creates a new Relationship RecordBuilder by copying an existing Relationship instance.
   * @param other The existing instance to copy.
   * @return A new Relationship RecordBuilder
   */
  public static io.github.petersonjr.metadatacrawler.model.Relationship.Builder newBuilder(io.github.petersonjr.metadatacrawler.model.Relationship other) {
    return new io.github.petersonjr.metadatacrawler.model.Relationship.Builder(other);
  }

  /**
   * RecordBuilder for Relationship instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Relationship>
    implements org.apache.avro.data.RecordBuilder<Relationship> {

    /** The table of the primary key column(s) */
    private java.lang.CharSequence primary_table;
    /** The primary key columns of this relationship */
    private java.util.List<java.lang.CharSequence> primary_columns;
    /** The table of the foreign key column(s). */
    private java.lang.CharSequence foreign_table;
    /** The foreign key columns of this relationship */
    private java.util.List<java.lang.CharSequence> foreign_columns;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Relationship.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.primary_table)) {
        this.primary_table = data().deepCopy(fields()[0].schema(), other.primary_table);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.primary_columns)) {
        this.primary_columns = data().deepCopy(fields()[1].schema(), other.primary_columns);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.foreign_table)) {
        this.foreign_table = data().deepCopy(fields()[2].schema(), other.foreign_table);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.foreign_columns)) {
        this.foreign_columns = data().deepCopy(fields()[3].schema(), other.foreign_columns);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Relationship instance
     * @param other The existing instance to copy.
     */
    private Builder(io.github.petersonjr.metadatacrawler.model.Relationship other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.primary_table)) {
        this.primary_table = data().deepCopy(fields()[0].schema(), other.primary_table);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.primary_columns)) {
        this.primary_columns = data().deepCopy(fields()[1].schema(), other.primary_columns);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.foreign_table)) {
        this.foreign_table = data().deepCopy(fields()[2].schema(), other.foreign_table);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.foreign_columns)) {
        this.foreign_columns = data().deepCopy(fields()[3].schema(), other.foreign_columns);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'primary_table' field.
      * The table of the primary key column(s)
      * @return The value.
      */
    public java.lang.CharSequence getPrimaryTable() {
      return primary_table;
    }

    /**
      * Sets the value of the 'primary_table' field.
      * The table of the primary key column(s)
      * @param value The value of 'primary_table'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder setPrimaryTable(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.primary_table = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'primary_table' field has been set.
      * The table of the primary key column(s)
      * @return True if the 'primary_table' field has been set, false otherwise.
      */
    public boolean hasPrimaryTable() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'primary_table' field.
      * The table of the primary key column(s)
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder clearPrimaryTable() {
      primary_table = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'primary_columns' field.
      * The primary key columns of this relationship
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getPrimaryColumns() {
      return primary_columns;
    }

    /**
      * Sets the value of the 'primary_columns' field.
      * The primary key columns of this relationship
      * @param value The value of 'primary_columns'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder setPrimaryColumns(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[1], value);
      this.primary_columns = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'primary_columns' field has been set.
      * The primary key columns of this relationship
      * @return True if the 'primary_columns' field has been set, false otherwise.
      */
    public boolean hasPrimaryColumns() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'primary_columns' field.
      * The primary key columns of this relationship
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder clearPrimaryColumns() {
      primary_columns = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'foreign_table' field.
      * The table of the foreign key column(s).
      * @return The value.
      */
    public java.lang.CharSequence getForeignTable() {
      return foreign_table;
    }

    /**
      * Sets the value of the 'foreign_table' field.
      * The table of the foreign key column(s).
      * @param value The value of 'foreign_table'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder setForeignTable(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.foreign_table = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'foreign_table' field has been set.
      * The table of the foreign key column(s).
      * @return True if the 'foreign_table' field has been set, false otherwise.
      */
    public boolean hasForeignTable() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'foreign_table' field.
      * The table of the foreign key column(s).
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder clearForeignTable() {
      foreign_table = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'foreign_columns' field.
      * The foreign key columns of this relationship
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getForeignColumns() {
      return foreign_columns;
    }

    /**
      * Sets the value of the 'foreign_columns' field.
      * The foreign key columns of this relationship
      * @param value The value of 'foreign_columns'.
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder setForeignColumns(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[3], value);
      this.foreign_columns = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'foreign_columns' field has been set.
      * The foreign key columns of this relationship
      * @return True if the 'foreign_columns' field has been set, false otherwise.
      */
    public boolean hasForeignColumns() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'foreign_columns' field.
      * The foreign key columns of this relationship
      * @return This builder.
      */
    public io.github.petersonjr.metadatacrawler.model.Relationship.Builder clearForeignColumns() {
      foreign_columns = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Relationship build() {
      try {
        Relationship record = new Relationship();
        record.primary_table = fieldSetFlags()[0] ? this.primary_table : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.primary_columns = fieldSetFlags()[1] ? this.primary_columns : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[1]);
        record.foreign_table = fieldSetFlags()[2] ? this.foreign_table : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.foreign_columns = fieldSetFlags()[3] ? this.foreign_columns : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Relationship>
    WRITER$ = (org.apache.avro.io.DatumWriter<Relationship>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Relationship>
    READER$ = (org.apache.avro.io.DatumReader<Relationship>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
