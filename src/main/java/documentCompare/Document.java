package documentCompare;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Document implements Comparable<Document> {

        // Partial Representation of a Document
        // You may need to add more...
        public String url;
        public String doc;
        public Map<String, Integer> docWords;
        private File file;
        private Scanner input;
        /**
         * Create a Document object given a URL to the document
         *
         * @param url
         *            is not null or an empty String
         */
        public Document(String url) throws IOException {

            // TODO: Implement this method

            this.file = new File(url);
            input = new Scanner(file);

            this.doc = "";
            this.docWords = new LinkedHashMap<>();

            //make document into a string and put into a hashmap
            while (input.hasNext())
            {
                String word = input.next();
                doc += " "+word;
                if (!docWords.containsKey(word)) {
                    docWords.put(word, 1);
                }
                else {
                    int count = docWords.get(word);
                    docWords.put(word, count+1);
                }
            }
            input.close();


        }

        /**
         * Compute the cosine similarity percentage between this Document and
         * another Document.
         *
         * @param otherDoc
         *            is not null
         * @return the cosine similarity percentage between this Document and
         *         another Document.
         */
        public int cosineSimilarity(Document otherDoc) {
            // TODO: Implement this method

            Map<String, Integer> doc1Words = this.docWords;
            Map<String, Integer> doc2Words = otherDoc.docWords;
            ArrayList<Integer> set1 = new ArrayList<>();
            ArrayList <Integer> set2 = new ArrayList<>();


            Iterator<String> itr1 = doc1Words.keySet().iterator();


            //mark down all the words doc1 contains into set arrays
            while (itr1.hasNext()) {
                String word = itr1.next();
                int count = doc1Words.get(word);
                set1.add(count);

                //if doc 2 contains doc1 word, add it to set 2
                if(doc2Words.containsKey(word)) {
                    set2.add(doc2Words.get(word));
                }
                else{
                    set2.add(0);
                }

            }

            //iterate the words left
            Iterator<String> itr2 = doc2Words.keySet().iterator();


            //adding all remaining words in doc2 to arrays
            while(itr2.hasNext()){
                String word = itr2.next();
                if(!doc1Words.containsKey(word)) {
                    int count = doc2Words.get(word);
                    set2.add(count);
                    set1.add(0);
                }
            }


            double csim100;
            double AdotB = 0;
            double absA = 0;
            double absB = 0;

            //calculate csim100
            for (int i = 0 ; i < set1.size(); i++){

                AdotB += (set1.get(i)*set2.get(i));
                absA += Math.pow(set1.get(i),2);
                absB += Math.pow(set2.get(i),2);
            }
            csim100 = 100*(AdotB/( Math.sqrt(absA)* Math.sqrt(absB)));

            return (int) csim100;
        }

        // You should not have to change any of the methods below this comment
        /**
         * Return a String that represents the URL for the document
         */
        public String toString() {
            // TODO: Implement this method
            return null;
        }

        /**
         * Compare two Document objects for equality
         *
         * @param other
         * @return true if this Document and the other Document represent the same
         *         document.
         */
        @Override
        public boolean equals(Object other) {

            if (other instanceof Document) {
                Document otherDoc = (Document) other;
                return (this.url.equals(otherDoc.url));
            } else {
                return false;
            }
        }

        /**
         * Compute the hashCode for this Document object
         *
         * @return the hashCode for this Document object
         */
        @Override
        public int hashCode() {
            return url.hashCode();
        }

        /**
         * Compare two Document objects. Allows for ordering of Document objects.
         *
         * @param other
         *            Document to compare this document with
         * @return 0 if the two Documents are equal, and a non-zero value otherwise
         */
        public int compareTo(Document other) {
            if (this.equals(other))
                return 0;
            else
                return url.compareTo(other.url);
        }

    }

