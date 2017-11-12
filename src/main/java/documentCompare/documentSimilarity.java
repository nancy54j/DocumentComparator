package documentCompare;

import java.util.*;

public class documentSimilarity {

        /**
         * Determine a set of document groups where a group of documents are more
         * similar to each other than to documents in a different group.
         *
         * @param docList
         *            is a List with at least two Document references from which we
         *            want to group documents by similarity
         * @param numGroups
         *            > 0 is the number of Document groups to create
         * @return a Map that represents how documents are grouped. Two documents
         *         that are in the same group will map to the same value and two
         *         documents that are not in the same group will map to different
         *         values.
         */
        public static Map<Document, Integer> groupSimilarDocuments(List<Document> docList, int numGroups) {

            // TODO: Implement this method
            Map<Document, Integer> groups = new HashMap<>() ;
            Map <Integer, DocumentPair> similarity = new HashMap<>();

//more groups than docs
            if(numGroups>docList.size()) {
                return null;
            }
//case 1: 1 group
            if (numGroups == 1) {
                for (int i = 0; i < docList.size(); i++) {

                    groups.put(docList.get(i), numGroups);

                }
            }


            //case 2: numGroups = number of documents in array
            else if (docList.size()== numGroups){


                for( int i = 0; i < docList.size(); i++) {

                    groups.put(docList.get(i), i);
                }

            }

            //case 3: need grouping
            else {
                //keeps track of groups left (indicated by i; ex: groups 1,2,5,6)
                ArrayList<Integer> pos = new ArrayList<>();

                //put all docs in their own groups
                for (int i = 0; i < docList.size(); i++) {
                    //Put group in a hashmap with key being document and value being the group number
                    groups.put(docList.get(i), i);
                    pos.add(i);

                    for (int j = i; j < docList.size(); j++) {
                        if (i != j) {
                            //make hashmap with key being the similarity and value being corresponding document pair
                            int sim = docList.get(i).cosineSimilarity(docList.get(j));

                            //note, group number of the second doc in groups is always larger than group number of first doc
                            similarity.put(sim, new DocumentPair(docList.get(i), docList.get(j)));
                        }
                    }

                }

                //while number of groups left is larger than numGroups
                while (pos.size() > numGroups) {
                    //get most similar pair
                    int maxValue = Collections.max(similarity.keySet());
                    DocumentPair temp = similarity.get(maxValue);

                    //if first doc in most similar pair is not in the same group as second doc in similar pair
                    if (groups.get(temp.pair[1]) != groups.get(temp.pair[0])) {
                        //remove group number of second doc (larger)
                        pos.remove(groups.get(temp.pair[1]));
                    }


                    int index = groups.get(temp.pair[1]);
                    int newIndex = groups.get(temp.pair[0]);
                    Iterator<Document> itr = groups.keySet().iterator();
                    //replace all the group numbers of the documents in the same group as temp.pair[1] with the group number of temp.pair[0]
                    while (itr.hasNext()){
                        Document doc = itr.next();
                        if (groups.get(doc) == index){
                            groups.put(doc , newIndex);
                        }
                    }


                    similarity.remove(maxValue);
                }

            }

            return groups;
        }

    }


