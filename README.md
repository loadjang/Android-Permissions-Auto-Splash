# Android-Permissions-Auto-Splash
Android Very Easy Permissions Request Auto Load  First Activity  Or Need Permission Request

Auto code


        Permissions permission;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Permissions.Build build=  new Permissions.Build();
        Permissions.permissionsCallback callback=new Permissions.permissionsCallback() {
            @Override
            public void onRequestPermissionsResult_GRANTED() {
                /* All Permission Granted
                */
            }

            @Override
            public void onRequestPermissionsResult_DENIED() {
             /*
              force default nothing   you use setForceFlag(false)
             */
            }
        };
        permission=build.setContext(Splash.this).setAllFlag(true).setCallback(callback).setForceFlag(true).build();
        permission.checkPermissions();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        permission.onRequestPermissionsResult(requestCode,permissions,grantResults);


    }
    


use need Request Permission very simple 


        {
        Permissions.Build build=  new Permissions.Build();
        Permissions.permissionsCallback callback=new Permissions.permissionsCallback() {
            @Override
            public void onRequestPermissionsResult_GRANTED() {
                /*  Permission Granted
                */
            }

            @Override
            public void onRequestPermissionsResult_DENIED() {
             /*
                    
             */
            }
        };
        permission=build.setContext(Splash.this).setAllFlag(false).setCallback(callback).setsetForceFlag(false).
          setMypermission(new String[]{Manifest.permission.CALL_PHONE}).build();
        
        Button delbtn=(Button)findViewById(R.id.delbtn);
        delbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Check Permissions
                 permission.checkPermissions();
            }
        });
     
        }
        }
         @Override
         public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
             permission.onRequestPermissionsResult(requestCode,permissions,grantResults);
          }
    
    
        

   
