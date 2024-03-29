package ar.edu.ubp.das.elastic;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.ElasticsearchException;

import ar.edu.ubp.das.beans.indexation.MetadataBean;
import ar.edu.ubp.das.beans.search.ResultsBean;
import ar.edu.ubp.das.beans.search.SearchBean;

public interface MetadataDao {
	public List<MetadataBean> get(Integer id, Boolean indexed) throws ElasticsearchException, Exception;
	public MetadataBean getId(String id) throws ElasticsearchException, Exception;
	public void deleteByWebsiteId(Integer id) throws ElasticsearchException, Exception;
	public void deleteByUserId(Integer id) throws ElasticsearchException, Exception;
	public void update(MetadataBean meta) throws ElasticsearchException, Exception;
	public void delete(String id) throws ElasticsearchException, Exception;
	public void updateBatch(List<MetadataBean> metadataList) throws ElasticsearchException, Exception;
	public void deleteBatch(List<MetadataBean> metadata) throws ElasticsearchException, Exception;
	public ResultsBean search(SearchBean search) throws ElasticsearchException, Exception;
	public void significantWords(SearchBean search) throws ElasticsearchException, Exception;
	public void increasePopularity(String id) throws IOException;
}
