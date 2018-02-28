package com.zee.wordcount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.io.WritableComparable;

public class EmailParticipants implements WritableComparable {

	private Set recievers;
	private String sender;
	private int recieverCnt;
	
	public EmailParticipants(String sender, Set recievers) {
		this.sender = sender;
        this.recievers = recievers;
        recieverCnt = recievers.size();
	}
	public String getSender() {
		return sender;
	}
	public Set getReciever() {
		return this.recievers;
	}
	public void setRecievers(Set recievers) {
		this.recievers=recievers;
	}
	public void setSender(String from) {
		this.sender=from;
	}
	@Override
	public void readFields(DataInput dataIp) throws IOException {
		// TODO Auto-generated method stub
		sender=dataIp.readUTF();
		recieverCnt=dataIp.readInt();
		recievers=new HashSet(recieverCnt);
		for(int i=0;i<recieverCnt;i++) {
			recievers.add(dataIp.readUTF());
		}
	}

	@Override
	public void write(DataOutput dataOp) throws IOException {
		dataOp.writeUTF(recievers.toString());
        dataOp.writeInt(recievers.size());
        Iterator rcvr = recievers.iterator();
        while (rcvr.hasNext()) {
            dataOp.writeUTF((String) rcvr.next());
        }
		
	}

	public int compareTo(EmailParticipants arg0) {
        return sender.compareTo(arg0.getSender());
    }
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
