
for num_nodes in {1..10}
do

  SECONDS=0
  new_nodes_num=$(( $num_nodes + $num_nodes))
#  echo $new_nodes_num
  gcloud container clusters resize spzc-reset-ip --num-nodes $new_nodes_num --zone us-central1-c -q > /dev/null
  echo Adding $new_nodes_num nodes took: $SECONDS seconds

  SECONDS=0
#  gcloud container clusters resize spzc-reset-ip --num-nodes $num_nodes --zone us-central1-c -q > /dev/null
  echo Removing $num_nodes old nodes nodes took: $SECONDS seconds

done
