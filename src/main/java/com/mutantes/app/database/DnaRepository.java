package com.mutantes.app.database;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.mutantes.app.entities.Dna;

@Repository
public class DnaRepository {

	BasicAWSCredentials creds = new BasicAWSCredentials("", "");
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
		    .withRegion("us-east-2")
		    .withCredentials(new AWSStaticCredentialsProvider(creds))
		    .build();
	DynamoDB dynamoDb = new DynamoDB(client);
	
	public JSONObject getRatio() throws Exception{
		
		// traer no mutantes
		Integer falseInt = 0;
		Condition mutantConditionFalse = new Condition()
		        .withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN( falseInt.toString() ));
		
		Map<String, Condition> keyConditionsFalse = new HashMap<>();
		keyConditionsFalse.put("mutant", mutantConditionFalse);
		
		QueryRequest requestFalse = new QueryRequest("dna");
		requestFalse.setIndexName("mutant-index");
		requestFalse.setSelect(Select.COUNT);
		requestFalse.setKeyConditions(keyConditionsFalse);
		QueryResult resultFalse = client.query(requestFalse);
		
		
		int countFalse = resultFalse.getCount();
		
		//System.out.println(countFalse);
		
		// traer mutantes
		Integer trueInt = 1;
		Condition mutantConditionTrue = new Condition()
		        .withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN( trueInt.toString() ));
		
		Map<String, Condition> keyConditionsTrue = new HashMap<>();
		keyConditionsTrue.put("mutant", mutantConditionTrue);
		
		QueryRequest requestTrue = new QueryRequest("dna");
		requestTrue.setIndexName("mutant-index");
		requestTrue.setSelect(Select.COUNT);
		requestTrue.setKeyConditions(keyConditionsTrue);
		QueryResult resultTrue = client.query(requestTrue);
		
		int countTrue = resultTrue.getCount();
		
		//System.out.println(countTrue);
		
		JSONObject obj = new JSONObject();
		obj.put("count_mutant_dna", countTrue);
		obj.put("count_human_dna", countFalse);
		obj.put("ratio", (countTrue/countFalse));
		
		return obj;
	}
	
	
	public void addDna(Dna dna) throws Exception{ 
		Table table = dynamoDb.getTable("dna");
		Integer mutantInt = dna.getMutant() ? 1 : 0;
		PutItemOutcome outcome = table.putItem(
		new Item().withPrimaryKey("dna_id", dna.getDna_id())
				  .with("mutant", mutantInt ) ) ;

		if (!Objects.nonNull(outcome))
			throw new Exception("Error gardando dna");
	}
}
