package com.example.oemded.code;

public class OEMBED_CODE {
    public enum STATUS{
        OK("0000"),
        ERROR("9999");

        private String status;

        STATUS(String status){
            this.status = status;
        }

        public String CODE(){
            return this.status;
        }
    }
}
