class PayAppLineItem extends React.Component {
  constructor(props) {
    super(props)
  }

  render() {
    return(
      <tr>
        <LabelCell value={this.props.item.pali_id}></LabelCell>
        <LabelCell value={this.props.item.cost_code}></LabelCell>
        <LabelCell value={this.props.item.description_of_work}></LabelCell>
        <LabelCell value={this.props.item.scheduled_value}></LabelCell>
        <LabelCell value={this.props.item.work_completed_from_previous_app}></LabelCell>
        <TextBoxCell
          value={this.props.item.work_completed_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateWorkCompletedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <TextBoxCell
          value={this.props.item.materials_presently_stored}
          onChange={(e) => {store.dispatch({type:'UpdateMaterialsPresentlyStored', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.total_completed_and_stored_presently}></LabelCell>
        <LabelCell value={this.props.item.percent_complete}></LabelCell>
        <LabelCell value={this.props.item.balance_to_finish}></LabelCell>
        <LabelCell value={this.props.item.work_retainage_from_previous_app}></LabelCell>
        <LabelCell value={this.props.item.materials_retainage_from_previous_app}></LabelCell>
        <LabelCell value={this.props.item.total_from_previous_app}></LabelCell>
        <TextBoxCell
          value={this.props.item.percent_work_retainage_retained_this_period}
          onChange={(e) => {store.dispatch({type:'UpdatePercentWorkRetainageRetainedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.dollar_work_retainage_retained_this_period}></LabelCell>
        <TextBoxCell
          value={this.props.item.percent_materials_retainage_retained_this_period}
          onChange={(e) => {store.dispatch({type:'UpdatePercentMaterialsRetainageRetainedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.dollar_materials_retainage_retained_this_period}></LabelCell>
        <TextBoxCell
          value={this.props.item.work_retainage_released_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateWorkRetainageReleasedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <TextBoxCell
          value={this.props.item.materials_retainage_released_this_period}
          onChange={(e) => {store.dispatch({type:'UpdateMaterialsRetainageReleasedThisPeriod', id: this.props.item.pali_id, value: e.target.value});}}>
        </TextBoxCell>
        <LabelCell value={this.props.item.work_retainage_currently_retained}></LabelCell>
        <LabelCell value={this.props.item.materials_retainage_currently_retained}></LabelCell>
        <LabelCell value={this.props.item.total_currently_retained}></LabelCell>
      </tr>
    )
  }
}

export default PayAppLineItem;
