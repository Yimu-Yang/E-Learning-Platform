package cmpe295.hungwenli.elearning;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature.IDFModel;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class TFIDFExample {

    public static void main(String[] args){

        System.setProperty("hadoop.home.dir", "/");

        // create a spark session
        SparkSession spark = SparkSession
                .builder()
                .appName("TFIDF Example")
                .config("spark.master", "local")
                .getOrCreate();

        // documents corpus. each row is a document.
        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, "Welcome to TutorialKart."),
                RowFactory.create(0.0, "Learn Spark at TutorialKart."),
                RowFactory.create(1.0, "Spark Mllib has TF-IDF.")
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });

        // import data with the schema
        Dataset<Row> sentenceData = spark.createDataFrame(data, schema);

        // break sentence to words
        Tokenizer tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words");
        Dataset<Row> wordsData = tokenizer.transform(sentenceData);

        // define Transformer, HashingTF
        int numFeatures = 10;
        HashingTF hashingTF = new HashingTF()
                .setInputCol("words")
                .setOutputCol("rawFeatures")
                .setNumFeatures(numFeatures);

        // transform words to feature vector
        Dataset<Row> featurizedData = hashingTF.transform(wordsData);

//        System.out.println("TF vectorized data\n----------------------------------------");
//        for(Row row:featurizedData.collectAsList()){
//            System.out.println("row after tf: " + row.get(3));
//        }
//
//        System.out.println("ToJSON: " + featurizedData.toJSON());

        // IDF is an Estimator which is fit on a dataset and produces an IDFModel
        IDF idf = new IDF().setInputCol("rawFeatures").setOutputCol("features");
        IDFModel idfModel = idf.fit(featurizedData);

        // The IDFModel takes feature vectors (generally created from HashingTF or CountVectorizer) and scales each column
        Dataset<Row> rescaledData = idfModel.transform(featurizedData);

//        System.out.println("TF-IDF vectorized data\n----------------------------------------");
//        for(Row row:rescaledData.collectAsList()){
//            System.out.println("row after idf: " + row.get(4));
//        }
//
//        System.out.println("Transformations\n----------------------------------------");
//        for(Row row:rescaledData.collectAsList()){
//            System.out.println("The final row: " + row);
//        }
        rescaledData.select("label", "features").show();

        spark.close();
    }
}
