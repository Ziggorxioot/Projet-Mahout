package com.application.mahout;

import java.util.List;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public interface FonctionReco {
	
	public List<RecommendedItem> getRecommendedItems(DataModel model, ItemSimilarity sim) ;

}
