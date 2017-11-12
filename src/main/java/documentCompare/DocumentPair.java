package documentCompare;

        import javax.print.Doc;
        import java.net.URL;
        import java.util.Scanner;

public class DocumentPair implements Comparable<DocumentPair> {

    // Partial Representation
    public Document doc1,doc2;
    public Document[] pair = new Document[2];

    /**
     * Create a new Document pair given two Documents
     *
     * @param doc1
     *            not null
     * @param doc2
     *            not null
     */
    public DocumentPair(Document doc1, Document doc2) {
        // TODO: Implement this method
        this.pair[0] = doc1;
        this.pair[1] = doc2;
    }

    /*
     *
     * @param other
     *            the other DocumentPair to compare this to
     *
     * @return a value less than 0 if this DocumentPair is less similar
     *         internally than the other DocumentPair, 0 if the similarity of
     *         the two pairs is the same, and a value > 0 if this pair is more
     *         similar than the other pair.
     */
    public int compareTo(DocumentPair other) {
        // TODO: Implement this method
        Document[] thisPair = this.pair;
        Document[] otherPair = other.pair;

        //find cosine similarity of each pair
        int thisSim = thisPair[0].cosineSimilarity(thisPair[1]);
        int otherSim = otherPair[0].cosineSimilarity(otherPair[1]);

        //if other pair is more similar, return -1
        if (thisSim < otherSim){
            return -1;
        }
        //if the same similarity, return 0
        else if (thisSim == otherSim){
            return 0;
        }

        //else, return 1
        else {
            return 1;
        }
    }

    // You should not have to implement/change anything below this comment

    /**
     * Compare two DocumentPair objects for equality.
     *
     * @param other
     *            is not null
     * @return true if this DocumentPair and the other DocumentPair represent
     *         the same two Document objects and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof DocumentPair) {
            DocumentPair other = (DocumentPair) obj;
            return ((this.doc1.equals(other.doc1) && this.doc2.equals(other.doc2))
                    || (this.doc1.equals(other.doc2) && (this.doc2.equals(other.doc1))));
        } else
            return false;

    }

    /**
     * Compute the hashCode for a DocumentPair
     *
     * @return the hashCode for this DocumentPair
     */
    @Override
    public int hashCode() {
        return doc1.hashCode() + doc2.hashCode();
    }

    /**
     * Return the first Document in the DocumentPair. The ordering of Document
     * objects in a DocumentPair is arbitrary.
     *
     * @return the first Document in the DocumentPair.
     */
    public Document getDoc1() {
        return doc1;
    }

    /**
     * Return the second Document in the DocumentPair. The ordering of Document
     * objects in a DocumentPair is arbitrary.
     *
     * @return the second Document in the DocumentPair.
     */
    public Document getDoc2() {
        return doc2;
    }

}


