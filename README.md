# Android-Permissions-Auto-Splash
Android Very Easy Permissions Request All Auto Load  First Activity  Or Need Permission Request

안드로이드 퍼미션을 미니페스트에서 읽어서 첫 액티비티에서 자동으로 처리해주는 라이브러리입니다.
자동 처리이외에도 상황별 퍼미션처리 가 쉽게 가능합니다.

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
    
    
        

   
