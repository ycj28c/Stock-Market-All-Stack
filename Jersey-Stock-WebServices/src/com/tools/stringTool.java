package com.tools;

import java.util.ArrayList;
import java.util.List;

public class stringTool {
	/** Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
	  * @param String words, int L
	  * @return List<String>
	  */ 
	public String fullJustify(String words, int L) {
		words = words.trim();
		String ss[] = words.split("\\s+");
		List<String> l = fullJustify(ss,L);
		StringBuilder res = new StringBuilder();
		for(String s:l){
			res.append(s);
			res.append("\n");
		}
		return res.toString();
	}
	/** Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
	  * @param String[] words, int L
	  * @return List<String>
	  */ 
	public List<String> fullJustify(String[] words, int L) {
        List<String> list = new ArrayList<String>();
		if(words.length<1&&L>=0){
			String temp= "";
			for(int i=0;i<L;i++){
				temp+=" ";
			}
			list.add(temp);
			return list;
		}
		if(L<0){
			String temp = "";
			list.add(temp);
			return list;
		}
		int startWord = 0;
		int lineLength = 0;
		for(int i=0;i<words.length;i++){
			String curString = words[i];
			if(curString.length()>L) return list; //if the word's length is larger than a line
			if(i!=0&&lineLength+1+curString.length()>L){//if the line plus blank plus cur word is bigger than the allow length		
				//arrange current line
				int blankReq = L-lineLength;
				int wordCnt = i-startWord;
				int blankInterval = wordCnt>1?blankReq/(wordCnt-1):0; //divider should not be 0
				String blankInter = "";
				for(int j=0;j<=blankInterval;j++){
					blankInter+=" ";
				}
				int blankPlus = wordCnt>1?blankReq%(wordCnt-1):0;
				String lineString = "";
				for(int j=startWord;j<i;j++){
					lineString += words[j];
					if(wordCnt-1>0){
						lineString+= blankInter;
						wordCnt--;
					}
					if(blankPlus>0){
						lineString += " ";
						blankPlus--;
					}
				}
				int len = lineString.length();
				for(int j=0;j<L-len;j++){ //add blank to the tail
					//System.out.println("debug"+lineString.length()+L);
					lineString+=" ";
				}
				//
				list.add(lineString);
				//set the line
				startWord = i;
				lineLength = curString.length();
			}
			else{		
				lineLength +=1; //plus one blank
				lineLength += curString.length(); //plus word
				if(i==0)
					lineLength = curString.length();
			}
			if(i==words.length-1){//if is the last words
				//System.out.println("debug");
				String lineString = "";
				for(int j=startWord;j<=i;j++){
					lineString += words[j];
					if(j!=i)
						lineString +=" ";
				}
				int len = lineString.length();
				for(int j=0;j<L-len;j++){ //add blank to the tail
					//System.out.println("debug"+lineString.length()+L);
					lineString+=" ";
				}
				list.add(lineString);
			}
		}
		return list;
    }
}
